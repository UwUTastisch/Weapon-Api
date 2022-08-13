package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponItemMidLevelUtils {

    public static ItemStack getWeaponItem(int rangeID, int level) {
        ItemStack stack = new ItemStack(Material.COAL);
        Weapon weapon = WeaponProvider.getWeapon(rangeID);
        stack.editMeta(itemMeta -> {
            itemMeta.setCustomModelData(rangeID);
            WeaponItemLowLevelUtils.setWeaponXP(itemMeta,0);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,weapon.getMagSize(level));
            WeaponItemLowLevelUtils.setWeaponTypeName(itemMeta,weapon.getName());
            WeaponItemLowLevelUtils.setRandomUUID(itemMeta);
        });
        return stack;
    }

}
