package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.events.ProjectileShootEvent;
import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

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
