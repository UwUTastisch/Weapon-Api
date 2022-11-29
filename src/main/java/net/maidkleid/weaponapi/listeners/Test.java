package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.events.ProjectileShootDamageEvent;
import net.maidkleid.weaponapi.events.ProjectileShootHitEvent;
import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.concurrent.ExecutionException;

public class Test implements Listener {

    @EventHandler
    public void test(ProjectileShootLaunchEvent event) {

        ProjectileShoot<?> projectileShoot = event.getProjectile();
        Projectile projectile = projectileShoot.getProjectile();
        Player handlingPlayer = projectileShoot.getWeaponInstance().getHandlingPlayer();
        assert projectile != null;
        projectile.setPassenger(handlingPlayer);
        // System.out.println("Launch: " + event.getProjectileShoot().weaponInstance.getWeapon().getName());
    }


    @EventHandler
    public void test2(ProjectileShootUpdateEvent event) {
        /*
        ProjectileShoot<?> projectileShoot = event.getProjectileShoot();
        System.out.println("Update: " + projectileShoot.weaponInstance.getWeapon().getName() + " " + projectileShoot.getProjectile().getChunk());

         */
    }

    @EventHandler
    public void test3(ProjectileShootHitEvent event) {
        //System.out.println( "HitEvent : " + event.getProjectile().getWeaponInstance());
    }

    @EventHandler
    public void test3(ProjectileShootDamageEvent event) {
        //System.out.println( "DamageEvent: " + event.getDamage() + event.getProjectile());
    }


}
