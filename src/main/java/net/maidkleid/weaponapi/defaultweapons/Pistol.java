package net.maidkleid.weaponapi.defaultweapons;

import net.maidkleid.weaponapi.utils.LevelMapper;
import net.maidkleid.weaponapi.utils.WeaponItemMidLevelUtils;
import net.maidkleid.weaponapi.weaponlib.AmmoType;
import net.maidkleid.weaponapi.weaponlib.ProjectileWeaponInstance;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import java.util.Vector;

public class Pistol implements Weapon {

    @Override
    public int getMagSize(int level) {
        return 12;
    }

    @Override
    public String getName() {
        return "Pistol";
    }

    @Override
    public AmmoType getAmmoType() {
        return AmmoType.LIGHT_AMMO;
    }

    @Override
    public ProjectileWeaponInstance<Snowball> getWeaponNewInstance(Player player, int slot, ItemStack stack) {
        return new ProjectileWeaponInstance<>(this, player, slot, stack, Snowball.class, 3.d);
    }

    @Override
    public Sound getShootSound(int level) {
        return Sound.BLOCK_ANVIL_BREAK;
    }

    @Override
    public double getBulletDamage(int level) {
        return 3;
    }

    @Override
    public long getReloadMagazineTime(int level) {
        return 5000;
    }

    @Override
    public long getReloadTime(int level) {
        return 300;
    }

}
