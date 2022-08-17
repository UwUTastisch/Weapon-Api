package net.maidkleid.weaponapi.weaponlib;

import net.kyori.adventure.text.Component;
import net.maidkleid.weaponapi.WeaponAPI;
import net.maidkleid.weaponapi.utils.WeaponItemLowLevelUtils;
import net.maidkleid.weaponapi.weaponlib.shoots.Shoot;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class WeaponInstance {

    protected final Weapon weapon;
    Player player;
    protected ItemStack itemStack;

    protected int slot;
    protected final UUID weaponUUID;
    protected boolean isMagReloading = false;
    private int reloadingMagTask;
    protected long reloadMagStart;
    protected long reloadStart;

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

    public int getLevel() {
        return weapon.getLevelMapper().getLevel(getXP());
    }

    public long getLeftReloadMagazineTime() {
        return weapon.getReloadMagazineTime(getLevel()) + reloadMagStart - System.currentTimeMillis();
    }

    public long getLeftReloadTime() {
        return weapon.getReloadTime(getLevel()) + reloadStart - System.currentTimeMillis();
    }

    public Player getHandlingPlayer() {
        return player;
    }

    public abstract @Nullable Shoot doShoot();

    public int tryStartReload() {
        isMagReloading = true;
        if(reloadingMagTask != 0) {
            return reloadingMagTask;
        }
        reloadMagStart = System.currentTimeMillis();
        reloadingMagTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(WeaponAPI.getPlugin(WeaponAPI.class),() -> {
            long leftReloadTime = getLeftReloadMagazineTime();
            boolean inHand = isInHand();
            if(inHand && leftReloadTime > 0) player.sendActionBar(Component.text("Is Reloading " + leftReloadTime));//sendMessage("Is Reloading");
            else if (inHand) reloadInstant();
            else breakReloading();
        }, 0 ,1);
        return reloadingMagTask;
    }

    protected void breakReloading() {
        isMagReloading = false;
        reloadMagStart = 0;
        Bukkit.getScheduler().cancelTask(reloadingMagTask);
        reloadingMagTask = 0;
    }

    public void reloadInstant() {
        player.sendActionBar(Component.text("Weapon has been Reloaded"));;
        breakReloading();
        setCurrentAmmo(weapon.getMagSize(getLevel()));
        tryUpdateStack();
    }

    protected void setCurrentAmmo(int size) {
        itemStack.editMeta(itemMeta -> {
            itemMeta.setDisplayName( "Ammo: " + size);
            WeaponItemLowLevelUtils.setAmmoTo(itemMeta,size);
        });
    }

    protected ItemStack returnUpdatedItemStack() {
        return itemStack.clone();
    }

    public boolean isStillInSlot() {
        ItemStack stack = player.getInventory().getStorageContents()[slot];
        return compareWeaponUUID(stack);
    }

    protected boolean isInHand() {
        return compareWeaponUUID(player.getItemInHand());
    }

    private boolean compareWeaponUUID(ItemStack stack) {
        try {
            return weaponUUID.equals(WeaponItemLowLevelUtils.getUUID(stack.getItemMeta()));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tryUpdateStack() {
        boolean stillInSlot = isStillInSlot();
        if(stillInSlot) player.setItemInHand(itemStack);
        return stillInSlot;
    }

    public int getMagSize() {
        return weapon.getMagSize(getLevel());
    }

    public Sound getShootSound() {
        return weapon.getShootSound(getLevel());
    }

    public UUID getUUID() {
        return WeaponItemLowLevelUtils.getUUID(itemStack.getItemMeta());
    }

    public double getBulletDamage() {
        return weapon.getBulletDamage(getLevel());
    }

    public double getBulletHeadShotDamage() {
        return weapon.getBulletHeadShotDamage(getLevel());
    }

    @Override
    public String toString() {
        return "WeaponInstance{" +
                "weapon=" + weapon +
                ", player=" + player +
                ", itemStack=" + itemStack +
                ", slot=" + slot +
                ", weaponUUID=" + weaponUUID +
                ", isReloading=" + isMagReloading +
                ", reloadingTask=" + reloadingMagTask +
                ", reloadStart=" + reloadMagStart +
                '}';
    }
}
