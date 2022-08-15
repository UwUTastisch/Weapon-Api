package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;

public interface ProjectileShootProvider {
    ProjectileShoot<?> getProjectile();
}
