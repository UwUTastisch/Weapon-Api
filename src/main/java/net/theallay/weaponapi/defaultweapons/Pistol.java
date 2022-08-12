package net.theallay.weaponapi.defaultweapons;

import net.theallay.weaponapi.weaponlib.AmmoType;
import net.theallay.weaponapi.weaponlib.Shoot;
import net.theallay.weaponapi.weaponlib.Weapon;
import net.theallay.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Pistol implements Weapon {

    @Override
    public int getMagSize() {
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
    public PistolInstance getWeaponInstance(Player player, ItemStack stack) {
        return new PistolInstance(this,player, stack);
    }

    public static class PistolInstance extends WeaponInstance {

        private PistolInstance(Pistol pistol, Player player, ItemStack itemStack) {
            super(pistol, player, itemStack);
        }

        @Override
        public int getCurrentAmmo() {
            return 0;
        }

        @Override
        public Shoot shoot() {
            return null;
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
