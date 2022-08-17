package net.maidkleid.weaponapi.defaultweapons;

import net.maidkleid.weaponapi.weaponlib.AmmoType;
import net.maidkleid.weaponapi.weaponlib.ProjectileWeaponInstance;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import net.maidkleid.weaponapi.weaponlib.shoots.Shoot;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PumpGun implements Weapon {
    @Override
    public double getSpread(int level) {
        return 0.3;
    }

    @Override
    public int getMagSize(int level) {
        return 10;
    }

    @Override
    public String getName() {
        return "PumpGun";
    }

    @Override
    public AmmoType getAmmoType() {
        return AmmoType.HEAVY_AMMO;
    }

    @Override
    public ProjectileWeaponInstance<Arrow> getWeaponNewInstance(Player player, int slot, ItemStack stack) {
        return new PumpGunInstance(this,player,slot,stack,Arrow.class,5, 2);
    }

    @Override
    public Sound getShootSound(int level) {
        return Sound.BLOCK_ANVIL_BREAK;
    }

    @Override
    public double getBulletDamage(int level) {
        return 2;
    }

    @Override
    public long getReloadMagazineTime(int level) {
        return 3000;
    }

    @Override
    public long getReloadTime(int level) {
        return 700;
    }

    @Override
    public Particle getParticle() {
        return Particle.ASH;
    }

    public static class PumpGunInstance extends ProjectileWeaponInstance<Arrow> {
        public PumpGunInstance(Weapon weapon, Player player, int slot, ItemStack itemStack, Class<Arrow> projectileClass, double velocity, double spread) {
            super(weapon, player, slot, itemStack, projectileClass, velocity);
        }

        @Override
        protected @Nullable ProjectileShoot<Arrow> onlyShootProjectile() {
            ProjectileShoot<Arrow> arrowProjectileShoot = super.onlyShootProjectile();
            if(arrowProjectileShoot != null) for (int i = 0; i < 10; i++) super.onlyShootProjectile();
            return arrowProjectileShoot;
        }

        @Override
        public ProjectileShoot<Arrow> doShoot() {
            ProjectileShoot<Arrow> arrowProjectileShoot = super.doShoot();
            if(arrowProjectileShoot!= null) {
                Player player = getHandlingPlayer();
                player.setVelocity(player.getVelocity().add(arrowProjectileShoot.getVelocity().normalize().multiply(-1)));
            }
            return arrowProjectileShoot;
        }
    }
}
