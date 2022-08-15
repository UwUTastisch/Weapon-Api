package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.utils.LevelMapper;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Weapon {


    int getMagSize(int level);

    String getName();

    AmmoType getAmmoType();

    WeaponInstance getWeaponInstance(Player player, int slot, ItemStack stack);

    Sound getShootSound(int level);

    default LevelMapper getLevelMapper() {
        return LevelMapper.DEFAULT_MAPPER;
    }

    double getBulletDamage(int level);

    default double getBulletHeadShotDamage(int level) {
        return getBulletDamage(level)*1.4d;
    }
}
