package net.theallay.weaponapi.commands;

import net.theallay.weaponapi.utils.ItemBuilder;
import net.theallay.weaponapi.weaponlib.Weapon;
import net.theallay.weaponapi.weaponlib.WeaponLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WeaponCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            WeaponLib weaponLib = WeaponLib.valueOf(args[0]);
            Weapon w = weaponLib.INSTANCE;
            if(sender instanceof Player player) {
                Location l = player.getLocation();
                World world = l.getWorld();
                world.dropItem(l, ItemBuilder.getWeaponItem(weaponLib));
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> l = new ArrayList<>();
        for (WeaponLib value : WeaponLib.values()) {
            l.add(value.name());
        }
        return l;
    }
}
