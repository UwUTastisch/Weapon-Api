package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Test implements Listener {

    @EventHandler
    public void test(ProjectileShootLaunchEvent event) {
        /*
        ProjectileShoot<?> projectileShoot = event.getProjectileShoot();
        Projectile projectile = projectileShoot.getProjectile();
        Player handlingPlayer = projectileShoot.weaponInstance.getHandlingPlayer();
        projectile.setPassenger(handlingPlayer);


         */
        // System.out.println("Launch: " + event.getProjectileShoot().weaponInstance.getWeapon().getName());
    }


    @EventHandler
    public void test2(ProjectileShootUpdateEvent event) {
        /*
        ProjectileShoot<?> projectileShoot = event.getProjectileShoot();
        System.out.println("Update: " + projectileShoot.weaponInstance.getWeapon().getName() + " " + projectileShoot.getProjectile().getChunk());

         */
    }


}
