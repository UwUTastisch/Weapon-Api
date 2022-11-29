package net.maidkleid.weaponapi;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.maidkleid.weaponapi.commands.WeaponCommand;
import net.maidkleid.weaponapi.listeners.OnClickListener;
import net.maidkleid.weaponapi.listeners.OnItemDropEvent;
import net.maidkleid.weaponapi.listeners.ProjectileHitListener;
import net.maidkleid.weaponapi.listeners.Test;
import net.maidkleid.weaponapi.utils.ProjectileUtils;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WeaponAPI extends JavaPlugin {

    private ProtocolManager protocolManager;

    public void onLoad() {

    }
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnItemDropEvent(), this);
        //Bukkit.getPluginManager().registerEvents(new Test(), this);
        Objects.requireNonNull(getCommand("weapon")).setExecutor(new WeaponCommand());
        try {
            protocolManager = ProtocolLibrary.getProtocolManager();

        } catch (Exception e) {
            getLogger().warning("SomeThing went wrong, Please Check ProtocolLib");
        }
        ProjectileShoot.reload(this);
        getLogger().info("all Default Weapons: " + WeaponProvider.getAllWeaponNames());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
