package net.maidkleid.weaponapi;

import net.maidkleid.weaponapi.commands.WeaponCommand;
import net.maidkleid.weaponapi.listeners.OnClickListener;
import net.maidkleid.weaponapi.listeners.ProjectileHitListener;
import net.maidkleid.weaponapi.listeners.Test;
import net.maidkleid.weaponapi.utils.ProjectileUtils;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WeaponAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), this);
        //Bukkit.getPluginManager().registerEvents(new Test(), this);
        Objects.requireNonNull(getCommand("weapon")).setExecutor(new WeaponCommand());
        ProjectileShoot.reload(this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
