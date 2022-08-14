package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.utils.WeaponItemLowLevelUtils;
import net.maidkleid.weaponapi.weaponlib.shoots.Shoot;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class WeaponInstance {

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

    protected ItemStack returnUpdatedItemStack() {
        return itemStack;
    }

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

    public int getMagSize() {
        return weapon.getMagSize(getXP());
    }

    public Sound getShootSound() {
        return weapon.getShootSound(getXP());
    }

    public UUID getUUID() {
        return WeaponItemLowLevelUtils.getUUID(itemStack.getItemMeta());
    }
}
