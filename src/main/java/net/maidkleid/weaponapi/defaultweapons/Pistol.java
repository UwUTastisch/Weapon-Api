package net.maidkleid.weaponapi.defaultweapons;

import net.maidkleid.weaponapi.weaponlib.AmmoType;
import net.maidkleid.weaponapi.weaponlib.Shoot;
import net.maidkleid.weaponapi.weaponlib.Weapon;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.Location;
import org.bukkit.Sound;
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

    public static class PistolInstance extends WeaponInstance {

        private PistolInstance(Pistol pistol, Player player, int slot, ItemStack itemStack) {
            super(pistol, player, slot, itemStack);
        }

        @Override
        public int getCurrentAmmo() {
            return 0;
        }

        @Override
        public Shoot shoot() {
            Location l = getHandlingPlayer().getEyeLocation();
            return new Shoot(this,
                    l,
                    l.getDirection().normalize().multiply(20));
        }

        @Override
        public boolean tryReload() {
            return false;
        }

        @Override
        protected ItemStack returnUpdatedItemStack() {
            return itemStack;
        }

    }


}
