package net.theallay.weaponapi.weaponlib;

import net.theallay.weaponapi.defaultweapons.Pistol;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Weapon {

    int getMagSize();

    String getName();

    AmmoType getAmmoType();

    WeaponInstance getWeaponInstance(Player player, int slot, ItemStack stack);
}
