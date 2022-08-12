package net.theallay.weaponapi;

import net.theallay.weaponapi.commands.WeaponCommand;
import net.theallay.weaponapi.listeners.OnClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WeaponAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnClickListener(), this);
        Objects.requireNonNull(getCommand("weapon")).setExecutor(new WeaponCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
