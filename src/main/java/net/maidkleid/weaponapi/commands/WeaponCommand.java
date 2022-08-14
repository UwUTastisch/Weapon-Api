package net.maidkleid.weaponapi.commands;

import net.maidkleid.weaponapi.utils.WeaponItemMidLevelUtils;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WeaponCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            Weapon weapon = WeaponProvider.getWeapon(args[0]);
            //sender.sendMessage("Weapon: " + weapon);
            //WeaponEnum weaponEnum = WeaponEnum.valueOf(args[0]);
            //Weapon w = weaponEnum.CLASS;
            if(sender instanceof Player player) {
                Location l = player.getLocation();
                World world = l.getWorld();
                int weaponID = WeaponProvider.getWeaponID(weapon.getName());
                world.dropItem(l, WeaponItemMidLevelUtils.getWeaponItem(WeaponProvider.getLowestCustomModelDataID(weaponID),0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //ArrayList<String> l = new ArrayList<>();
        //for (WeaponEnum value : WeaponEnum.values()) {
        //    l.add(value.name());
        //}
        return WeaponProvider.getAllWeaponNames();
    }
}
