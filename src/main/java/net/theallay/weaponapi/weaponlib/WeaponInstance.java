package net.theallay.weaponapi.weaponlib;

import net.theallay.weaponapi.utils.WeaponItemLowLevelUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class WeaponInstance implements Weapon {

    protected final Weapon weapon;
    private final Player player;
    protected ItemStack itemStack;

    protected int slot;
    protected final UUID weaponUUID;


    protected WeaponInstance(Weapon weapon, Player player, int slot , ItemStack itemStack) {
        this.weapon = weapon;
        this.player = player;
        this.itemStack = itemStack;
        this.slot = slot;
        this.weaponUUID = WeaponItemLowLevelUtils.getUUID(itemStack.getItemMeta());
    }

    /**
     * Before using Check what you're doing with {@link WeaponInstance}.isStillInSlot()
     * @return -500 if the ItemStack is not anymore in the current InventorySlot
     */
    public int getCurrentAmmo() {
        if(!isStillInSlot()) return -500;
        try {
            return WeaponItemLowLevelUtils.getCurrentAmmo(itemStack.getItemMeta());
        } catch (Exception e) {
            int magsize = getMagSize();
            itemStack.editMeta(itemMeta -> WeaponItemLowLevelUtils.setAmmoTo(itemMeta,magsize));
            return magsize;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getSlot() {
        return slot;
    }

    public int getXP() {
        return WeaponItemLowLevelUtils.getWeaponXP(itemStack.getItemMeta());
    }

    public Player getHandlingPlayer() {
        return player;
    }

    public abstract Shoot shoot();

    public abstract boolean tryReload();

    protected abstract ItemStack returnUpdatedItemStack();

    public boolean isStillInSlot() {
        ItemStack stack = player.getInventory().getStorageContents()[slot];
        try {
            return weaponUUID.equals(
                    WeaponItemLowLevelUtils.getUUID(stack.getItemMeta())
            );
        } catch (Exception e) {
            return false;
        }

    }
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
    public @Nullable WeaponInstance getWeaponInstance(Player player, int slot, ItemStack itemStack) {
        return weapon.getWeaponInstance(player, slot, itemStack);
    }
}
