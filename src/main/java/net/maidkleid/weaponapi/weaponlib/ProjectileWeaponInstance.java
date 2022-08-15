package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public abstract class ProjectileWeaponInstance<T extends Projectile> extends WeaponInstance {

    private final Class<T> projectileClass;

    protected ProjectileWeaponInstance(Weapon weapon, Player player, int slot, ItemStack itemStack, Class<T> projectileClass) {
        super(weapon, player, slot, itemStack);
        this.projectileClass = projectileClass;
    }
    public ProjectileShoot<T> doShoot() {
        Location l = getHandlingPlayer().getEyeLocation();
        return ProjectileShoot.shootWeapon(this,
                l,
                l.getDirection().normalize().multiply(20), projectileClass);
    }
}
