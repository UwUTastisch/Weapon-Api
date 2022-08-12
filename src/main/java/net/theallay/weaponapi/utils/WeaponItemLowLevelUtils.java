package net.theallay.weaponapi.utils;

import net.theallay.weaponapi.WeaponAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class WeaponItemLowLevelUtils {

    private static final NamespacedKey ammoKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"ammo");
    private static final NamespacedKey weaponTypeKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"weapon");
    private static final NamespacedKey uuidKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"uuid");
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

    public static void giveRandomUUID(ItemMeta itemMeta) {
        itemMeta.getPersistentDataContainer().set(uuidKey,PersistentDataType.STRING, UUID.randomUUID().toString());
    }

    public static UUID getUUID(ItemMeta itemMeta) throws NullPointerException {
        return UUID.fromString(itemMeta.getPersistentDataContainer().get(uuidKey,PersistentDataType.STRING));
    }

}
