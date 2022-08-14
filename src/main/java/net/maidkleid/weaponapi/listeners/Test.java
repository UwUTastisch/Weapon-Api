package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.events.ProjectileShootEvent;
import net.maidkleid.weaponapi.events.ProjectileShootLaunchEvent;
import net.maidkleid.weaponapi.events.ProjectileShootUpdateEvent;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class Test implements Listener {

    @EventHandler
    public void test(ProjectileShootLaunchEvent event) {
        System.out.println("Launch: " + event.getProjectileShoot().weaponInstance.getWeapon().getName());
    }

    @EventHandler
    public void test2(ProjectileShootUpdateEvent event) {
        System.out.println("Update: " + event.getProjectileShoot().weaponInstance.getWeapon().getName());
    }

}
