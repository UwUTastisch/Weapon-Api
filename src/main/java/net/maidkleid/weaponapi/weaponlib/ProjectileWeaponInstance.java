package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public class ProjectileWeaponInstance<T extends Projectile> extends WeaponInstance {

    private final Class<T> projectileClass;
    private final double velocity;

    public ProjectileWeaponInstance(Weapon weapon, Player player, int slot, ItemStack itemStack, Class<T> projectileClass, double velocity) {
        super(weapon, player, slot, itemStack);
        this.projectileClass = projectileClass;
        this.velocity = velocity;
    }
    public ProjectileShoot<T> doShoot() {
        if(getCurrentAmmo() <= 0) {
            tryStartReload();
            return null;
        }
        setCurrentAmmo(getCurrentAmmo()-1);
        if(!tryUpdateStack()) return null;
        Location l = getHandlingPlayer().getEyeLocation();
        return ProjectileShoot.shootWeapon(this,
                l,
                l.getDirection().normalize().multiply(velocity), projectileClass);
    }

}
