package net.maidkleid.weaponapi.weaponlib.shoots;

import net.maidkleid.weaponapi.WeaponAPI;
import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import net.maidkleid.weaponapi.utils.ProjectileUtils;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
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
        System.out.println("Projectile launched!");
        return projectile;
    }

    @Override
    protected boolean doUpdateTick() {
        Location location = projectile.getLocation();
        if(location.equals(tickPosition)) {
            projectile.remove();
            return false;
        }
        tickPosition = location;
        return true;
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

    private static @Nullable Shoot getShootForProjectile(Projectile projectile) {
        if(!projectileList.contains(projectile)) return null;
        UUID uuid = ProjectileUtils.getUUID(projectile);
        return shootHashMap.get(uuid);
    }

    public static <T extends Projectile> @Nullable ProjectileShoot<T> shootWeapon(WeaponInstance weaponInstance, Location startPosition, Vector velocity, Class<T> projectileClass) {
        ProjectileShoot<T> shoot = new ProjectileShoot<>(weaponInstance, startPosition, velocity, projectileClass);
        Projectile p = shoot.launch();
        ProjectileShootLaunchEvent event = new ProjectileShootLaunchEvent(shoot);
        Bukkit.getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            p.remove();
            return null;
        }
        p.setGlowing(true);
        p.setVelocity(velocity);
        p.setSilent(true);
        ProjectileUtils.setShootWeaponUUID(p,weaponInstance.getUUID());
        shootHashMap.put(ProjectileUtils.setRandomUUID(p), shoot);
        projectileList.add(p);
        return shoot;
    }



}
