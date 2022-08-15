package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.events.ProjectileShootHitEvent;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {
        if(event instanceof ProjectileShootHitEvent) return;
        ProjectileShoot<?> shoot = ProjectileShoot.getShootForProjectile(event.getEntity());
        if(shoot != null) shoot.callHitEvent(event);
    }

}
