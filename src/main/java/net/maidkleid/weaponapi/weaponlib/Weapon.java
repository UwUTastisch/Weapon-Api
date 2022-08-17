package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.utils.LevelMapper;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface Weapon {


    default Vector applySpread(Vector vector, int level) {
        double spread = getSpread(level);
        double angleHorizontal = (Math.random() * spread) - (spread / 2);
        double angleVertical = (Math.random() * spread) - (spread / 2);
        return vector.rotateAroundY(angleHorizontal);//vector.rotateAroundX(angleHorizontal)//.rotateAroundY(angleVertical);//.rotateAroundY(angleVertical).rotateAroundZ(angleVertical);
    }

    double getSpread(int level);

    int getMagSize(int level);

    String getName();

    AmmoType getAmmoType();

    WeaponInstance getWeaponNewInstance(Player player, int slot, ItemStack stack);

    Sound getShootSound(int level);

    default LevelMapper getLevelMapper() {
        return LevelMapper.DEFAULT_MAPPER;
    }

    double getBulletDamage(int level);

    default double getBulletHeadShotDamage(int level) {
        return getBulletDamage(level)*1.4d;
    }

    long getReloadMagazineTime(int level);

    long getReloadTime(int level);

    Particle getParticle();

    /**
     * Defines the ParticleTraceDensity
     * @return value > 0;
     */
    default double getParticleDensity() {
        return 0.2;
    }
}
