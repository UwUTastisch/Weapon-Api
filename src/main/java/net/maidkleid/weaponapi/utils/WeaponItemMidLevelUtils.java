package net.maidkleid.weaponapi.utils;

import net.kyori.adventure.text.Component;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WeaponItemMidLevelUtils {



    public static ItemStack getWeaponItem(int customModelData, int level) {
        ItemStack stack = new ItemStack(Material.GLASS);
        Weapon weapon = WeaponProvider.getWeapon(customModelData);
        stack.editMeta(itemMeta -> {
            WeaponItemLowLevelUtils.setWeaponXP(itemMeta,0);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,weapon.getMagSize(level));
            WeaponItemLowLevelUtils.setWeaponTypeName(itemMeta,weapon.getName());
            WeaponItemLowLevelUtils.setRandomUUID(itemMeta);
            makeCosmetics(itemMeta,customModelData, level);
        });
        System.out.println("Gen new Weapon: " + stack);
        return stack;
    }

    public static void makeCosmetics(ItemMeta meta, int customModelData, int level) {
        Weapon weapon = WeaponProvider.getWeapon(customModelData);
        meta.setCustomModelData(customModelData);
        meta.displayName(Component.text(weapon.getName()));
        meta.lore(weapon.getLore(level));
    }
}
