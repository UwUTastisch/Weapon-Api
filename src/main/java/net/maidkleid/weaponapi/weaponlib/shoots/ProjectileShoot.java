package net.maidkleid.weaponapi.weaponlib.shoots;

import net.maidkleid.weaponapi.weaponlib.Shoot;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.Location;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class ProjectileShoot extends Shoot {

    public <T extends Projectile> ProjectileShoot(WeaponInstance weaponInstance, Location startPosition, Vector velocity, Class<T> t) {
        super(weaponInstance, startPosition, velocity);
    }


}
