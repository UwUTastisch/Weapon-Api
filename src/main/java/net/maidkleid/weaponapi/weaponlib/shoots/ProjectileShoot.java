package net.maidkleid.weaponapi.weaponlib.shoots;

import net.maidkleid.weaponapi.WeaponAPI;
import net.maidkleid.weaponapi.events.ProjectileShootDamageEvent;
import net.maidkleid.weaponapi.events.ProjectileShootHitEvent;
import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import net.maidkleid.weaponapi.utils.ParticleTrace;
import net.maidkleid.weaponapi.utils.ProjectileUtils;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ProjectileShoot<T extends Projectile> extends Shoot {

    private final Class<T> projectileClass;
    private T projectile;
    private ProjectileShoot(WeaponInstance weaponInstance, Location startPosition, Vector velocity, Class<T> projectileClass) {
        super(weaponInstance, startPosition, velocity);
        this.projectileClass = projectileClass;
    }

    public @Nullable T getProjectile() {
        return projectile;
    }

    private T launch() {
        projectile = weaponInstance.getHandlingPlayer().launchProjectile(projectileClass);
        //System.out.println("Projectile launched!" + projectile);
        return projectile;
    }

    @Override
    protected boolean doUpdateTick() {
        Location oldTickPosition = tickPosition;
        tickPosition = projectile.getLocation();
        boolean b = addTickPositionToTrace();
        if(!b) projectile.remove();
        else ParticleTrace.spawnParticleTrace(tickPosition,oldTickPosition,weaponInstance.getWeapon().getParticle(), weaponInstance.getWeapon().getParticleDensity());
        return b;
    }



    //-------------------STATIC---------------------

    //projectiles randomUUID
    private static final HashMap<UUID,ProjectileShoot<?>> shootHashMap = new HashMap<>();
    private static final Set<Projectile> projectileList = new HashSet<>();
    private static int taskID;

    public static void reload(WeaponAPI api) {
        if(taskID != 0) {
            Bukkit.getScheduler().cancelTask(taskID);
        }
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(api, ProjectileShoot::updateAllProjectiles,0,1);
    }

    private static void updateAllProjectiles() {
        projectileList.removeIf(p -> {
            try {
                UUID shootUUID = ProjectileUtils.getUUID(p);
                Location location = p.getLocation();
                if(hasUnloadedNeighbourChunk(p.getChunk()) || location.getY()>= 400) p.remove();
                if(p instanceof Arrow arrow) if(arrow.isInBlock()) arrow.remove();
                if (p.isDead() || !p.isValid()) {
                    shootHashMap.remove(shootUUID);
                    return true;
                } else {
                    ProjectileShoot<?> projectileShoot = shootHashMap.get(shootUUID);
                    if(!projectileShoot.doUpdateTick()) return true;
                    Bukkit.getPluginManager().callEvent(new ProjectileShootUpdateEvent(projectileShoot));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
            return false;
        });
    }

    private static boolean hasUnloadedNeighbourChunk(Chunk chunk) {
        World world = chunk.getWorld();
        int x = chunk.getX();
        int z = chunk.getZ();
        return (!chunk.isLoaded()
                || !world.getChunkAt(x,z+2).isEntitiesLoaded()
                || !world.getChunkAt(x,z-2).isEntitiesLoaded()
                || !world.getChunkAt(x+2,z).isEntitiesLoaded()
                || !world.getChunkAt(x-2,z).isEntitiesLoaded());
    }

    public static @Nullable ProjectileShoot<?> getShootForProjectile(Projectile projectile) {
        if(!projectileList.contains(projectile)) return null;
        UUID uuid = ProjectileUtils.getUUID(projectile);
        return shootHashMap.get(uuid);
    }

    public static <T extends Projectile> @Nullable ProjectileShoot<T> shootWeapon(WeaponInstance weaponInstance, Location startPosition, Vector velocity, Class<T> projectileClass) {
        ProjectileShoot<T> shoot = new ProjectileShoot<>(weaponInstance, startPosition, velocity, projectileClass);
        Projectile p = shoot.launch();
        ProjectileShootLaunchEvent event = new ProjectileShootLaunchEvent(shoot);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) {
            p.remove();
            return null;
        }
        //;
        //for (Player nearbyPlayer : startPosition.getNearbyPlayers(30)) {
        startPosition.getWorld().playSound(startPosition,weaponInstance.getShootSound(),1,1);
            //startPosition.playSound(nearbyPlayer,weaponInstance.getShootSound(),1,1);
        //}

        p.setGlowing(true);
        p.setVelocity(velocity);
        p.setSilent(true);
        ProjectileUtils.setShootWeaponUUID(p,weaponInstance.getUUID());
        shootHashMap.put(ProjectileUtils.setRandomUUID(p), shoot);
        projectileList.add(p);
        return shoot;
    }


    public void callHitEvent(ProjectileHitEvent event) {
        ProjectileShootHitEvent newEvent = null;
        if(event.getHitEntity() != null && event.getHitBlock() != null) {
            newEvent = new ProjectileShootHitEvent(this, event.getHitEntity(), event.getHitBlock(), event.getHitBlockFace());
        } else if(event.getHitEntity() != null) {
            newEvent = new ProjectileShootHitEvent(this, event.getHitEntity());
        } else if(event.getHitBlock() != null) {
            newEvent = new ProjectileShootHitEvent(this, event.getHitBlock());
        }
        onHit(event, newEvent);
    }

    private void onHit(ProjectileHitEvent event, ProjectileShootHitEvent newEvent) {
        if(newEvent == null) return;
        if(!newEvent.callEvent()) {
            event.setCancelled(false);
            return;
        }
        event.setCancelled(true);
        Projectile entity = event.getEntity();
        entity.remove();
        Entity hitEntity = event.getHitEntity();
        if(hitEntity == null) return;
        onHitEntity(event, hitEntity);
    }

    private void onHitEntity(ProjectileHitEvent event, Entity hitEntity) {
        ProjectileShootDamageEvent damageEvent = new ProjectileShootDamageEvent(hitEntity,this);
        if(!(event.getHitEntity() instanceof LivingEntity livingEntity)) {
            return;
        }
        if(damageEvent.callEvent()) {
            onDamage(damageEvent, livingEntity);
        }
    }

    protected void onDamage(ProjectileShootDamageEvent damageEvent, LivingEntity livingEntity) {
        livingEntity.setNoDamageTicks(0);
        livingEntity.damage(damageEvent.getFinalDamage());
        livingEntity.setLastDamageCause(damageEvent);
        livingEntity.setVelocity(livingEntity.getVelocity().add(damageEvent.getKnockBack()));
    }
}
