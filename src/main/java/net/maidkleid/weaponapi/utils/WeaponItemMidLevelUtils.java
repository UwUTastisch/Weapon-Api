package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponEnum;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponItemMidLevelUtils {

    public static ItemStack getWeaponItem(WeaponEnum weaponEnum) {
        ItemStack stack = new ItemStack(Material.COAL);
        Weapon weapon = weaponEnum.CLASS;
        stack.editMeta(itemMeta -> {
            itemMeta.setCustomModelData(weaponEnum.ordinal());
            WeaponItemLowLevelUtils.setWeaponXP(itemMeta,0);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,weapon.getMagSize());
            WeaponItemLowLevelUtils.setWeaponTypeName(itemMeta,weapon.getName());
            WeaponItemLowLevelUtils.setRandomUUID(itemMeta);
        });
        return stack;
    }

}
