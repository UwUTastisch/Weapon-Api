package net.maidkleid.weaponapi.defaultweapons;

import net.maidkleid.weaponapi.weaponlib.AmmoType;
import net.maidkleid.weaponapi.weaponlib.ProjectileWeaponInstance;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

public class Pistol implements Weapon {

    @Override
    public double getSpread(int level) {
        return 0.1;
    }

    @Override
    public int getMagSize(int level) {
        return 15;
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
        return new ProjectileWeaponInstance<>(this, player, slot, stack, Snowball.class, 20.d);
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

    @Override
    public Particle getParticle() {
        return Particle.CRIT;
    }


}
