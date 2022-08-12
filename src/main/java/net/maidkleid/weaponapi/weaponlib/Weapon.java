package net.maidkleid.weaponapi.weaponlib;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Weapon {

    int getMagSize();

    String getName();

    AmmoType getAmmoType();

    WeaponInstance getWeaponInstance(Player player, int slot, ItemStack stack);
}
