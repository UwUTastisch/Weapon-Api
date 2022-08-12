package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.WeaponAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class WeaponItemLowLevelUtils {

    private static final NamespacedKey ammoKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"ammo");
    private static final NamespacedKey weaponTypeKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"weapon");
    private static final NamespacedKey uuidKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"uuid");
    private static final NamespacedKey xpKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"xp");
    //private static final NamespacedKey levelKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"xp");

    public static void setAmmoTo(@NotNull ItemMeta itemMeta, int amount) {
        itemMeta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, amount);
    }

    public static int getCurrentAmmo(@NotNull ItemMeta itemMeta) throws NullPointerException {
        return itemMeta.getPersistentDataContainer().get(ammoKey, PersistentDataType.INTEGER);
    }

    public static void setWeaponTypeName(@NotNull ItemMeta itemMeta, @NotNull String string) {
        itemMeta.getPersistentDataContainer().set(weaponTypeKey, PersistentDataType.STRING, string);
    }

    public static String getWeaponTypeName(@NotNull ItemMeta itemMeta) throws NullPointerException {
        return itemMeta.getPersistentDataContainer().get(weaponTypeKey, PersistentDataType.STRING);
    }

    public static void setRandomUUID(@NotNull ItemMeta itemMeta) {
        itemMeta.getPersistentDataContainer().set(uuidKey,PersistentDataType.STRING, UUID.randomUUID().toString());
    }

    public static @NotNull UUID getUUID(@NotNull ItemMeta itemMeta) throws NullPointerException {
        return UUID.fromString(itemMeta.getPersistentDataContainer().get(uuidKey,PersistentDataType.STRING));
    }

    public static int getWeaponXP(@NotNull ItemMeta itemMeta) throws NullPointerException {
        return itemMeta.getPersistentDataContainer().get(xpKey, PersistentDataType.INTEGER);
    }

    public static void setWeaponXP(ItemMeta itemMeta, int amount) {
        itemMeta.getPersistentDataContainer().set(xpKey, PersistentDataType.INTEGER, amount);
    }

}
