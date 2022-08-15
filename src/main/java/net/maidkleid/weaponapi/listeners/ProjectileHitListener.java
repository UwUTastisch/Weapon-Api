package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import net.maidkleid.weaponapi.weaponlib.shoots.Shoot;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {
        ProjectileShoot<?> shoot = ProjectileShoot.getShootForProjectile(event.getEntity());
        Entity hitEntity = event.getHitEntity();
        shoot.callEntityHitEvent(event);
    }

}
