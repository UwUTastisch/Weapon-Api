package net.theallay.weaponapi.weaponlib;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class WeaponInstance implements Weapon {

    protected final Weapon weapon;
    private final Player player;
    protected final ItemStack itemStack;

    protected WeaponInstance(Weapon weapon, Player player, ItemStack itemStack) {
        this.weapon = weapon;
        this.player = player;
        this.itemStack = itemStack;
    }

    public abstract int getCurrentAmmo();

    public Player getHandlingPlayer() {
        return player;
    }

    public abstract Shoot shoot();

    public abstract boolean tryReload();

    protected abstract ItemStack returnUpdatedItemStack();

    @Override
    public int getMagSize() {
        return weapon.getMagSize();
    }

    @Override
    public String getName() {
        return weapon.getName();
    }

    @Override
    public AmmoType getAmmoType() {
        return weapon.getAmmoType();
    }

    @Override
    public WeaponInstance getWeaponInstance(Player player, ItemStack itemStack) {
        return weapon.getWeaponInstance(player, itemStack);
    }
}
