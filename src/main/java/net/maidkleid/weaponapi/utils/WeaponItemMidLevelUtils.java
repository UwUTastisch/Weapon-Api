package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponItemMidLevelUtils {

    public static ItemStack getWeaponItem(int customModelData, int level) {
        ItemStack stack = new ItemStack(Material.GLASS);
        Weapon weapon = WeaponProvider.getWeapon(customModelData);
        stack.editMeta(itemMeta -> {
            itemMeta.setCustomModelData(customModelData);
            WeaponItemLowLevelUtils.setWeaponXP(itemMeta,0);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,weapon.getMagSize(level));
            WeaponItemLowLevelUtils.setWeaponTypeName(itemMeta,weapon.getName());
            WeaponItemLowLevelUtils.setRandomUUID(itemMeta);
        });
        System.out.println("Gen new Weapon: " + stack);
        return stack;
    }

}
