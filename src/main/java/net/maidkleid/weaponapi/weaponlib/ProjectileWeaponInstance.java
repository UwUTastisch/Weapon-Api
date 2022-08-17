package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ProjectileWeaponInstance<T extends Projectile> extends WeaponInstance {

    private final Class<T> projectileClass;
    private final double velocity;


    public ProjectileWeaponInstance(Weapon weapon, Player player, int slot, ItemStack itemStack, Class<T> projectileClass, double velocity) {
        super(weapon, player, slot, itemStack);
        this.projectileClass = projectileClass;
        this.velocity = velocity;
    }
    public ProjectileShoot<T> doShoot() {
        if(isMagReloading) return null;
        int currentAmmo = getCurrentAmmo();
        if(currentAmmo <= 0) {
            tryStartReload();
            return null;
        }
        if(getLeftReloadTime() > 0) return null;
        ProjectileShoot<T> tProjectileShoot = onlyShootProjectile();
        reloadStart = System.currentTimeMillis();
        if(tProjectileShoot == null) return null;
        int newCurrentAmmo = currentAmmo - 1;
        setCurrentAmmo(newCurrentAmmo);
        if(newCurrentAmmo <= 0) tryStartReload();
        if(!tryUpdateStack()) return null;
        return tProjectileShoot;
    }

    @Nullable
    protected ProjectileShoot<T> onlyShootProjectile() {
        Location l = getHandlingPlayer().getEyeLocation();
        return ProjectileShoot.shootWeapon(this,
                l,
                weapon.applySpread(l.getDirection().normalize().multiply(velocity),getLevel()), projectileClass);
    }

}
