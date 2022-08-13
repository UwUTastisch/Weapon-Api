package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponLib;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponItemMidLevelUtils {

    public static ItemStack getWeaponItem(WeaponLib weaponLib) {
        ItemStack stack = new ItemStack(Material.COAL);
        Weapon weapon = weaponLib.INSTANCE;
        stack.editMeta(itemMeta -> {
            itemMeta.setCustomModelData(weaponLib.ordinal());
            WeaponItemLowLevelUtils.setWeaponXP(itemMeta,0);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,weapon.getMagSize());
            WeaponItemLowLevelUtils.setWeaponTypeName(itemMeta,weapon.getName());
            WeaponItemLowLevelUtils.setRandomUUID(itemMeta);
        });
        return stack;
    }

}
