package net.maidkleid.weaponapi.defaultweapons;

import net.maidkleid.weaponapi.utils.LevelMapper;
import net.maidkleid.weaponapi.utils.WeaponItemMidLevelUtils;
import net.maidkleid.weaponapi.weaponlib.AmmoType;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
    public PistolInstance getWeaponInstance(Player player, int slot, ItemStack stack) {
        return new PistolInstance(this,player, slot, stack);
    }

    @Override
    public Sound getShootSound(int level) {
        return Sound.BLOCK_ANVIL_BREAK;
    }

    @Override
    public LevelMapper getLevelMapper() {
        return WeaponItemMidLevelUtils.getDefaultLevelMapper();
    }

    public static class PistolInstance extends WeaponInstance {

        private PistolInstance(Pistol pistol, Player player, int slot, ItemStack itemStack) {
            super(pistol, player, slot, itemStack);
        }

        @Override
        public int getCurrentAmmo() {
            return 0;
        }

        @Override
        public ProjectileShoot<Arrow> doShoot() {
            Location l = getHandlingPlayer().getEyeLocation();
            return ProjectileShoot.shootWeapon(this,
                    l,
                    l.getDirection().normalize().multiply(20), Arrow.class);
        }

        @Override
        public boolean tryReload() {
            return false;
        }

    }


}
