package net.maidkleid.weaponapi.utils;

import net.maidkleid.weaponapi.WeaponAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class ProjectileUtils {

    private static final NamespacedKey uuidKey = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"uuid");
    private static final NamespacedKey shootWeaponUUID = new NamespacedKey(WeaponAPI.getPlugin(WeaponAPI.class),"shootWeaponUUID");

    public static UUID setRandomUUID(@NotNull Projectile projectile) {
        UUID uuid = UUID.randomUUID();
        projectile.getPersistentDataContainer().set(uuidKey,PersistentDataType.STRING, uuid.toString());
        return uuid;
    }

    public static @NotNull UUID getUUID(@NotNull Projectile projectile) throws NullPointerException {
        return UUID.fromString(Objects.requireNonNull(projectile.getPersistentDataContainer().get(uuidKey, PersistentDataType.STRING)));
    }
    public static void setShootWeaponUUID(@NotNull Projectile projectile, UUID uuid) {
        projectile.getPersistentDataContainer().set(shootWeaponUUID,PersistentDataType.STRING, uuid.toString());
    }

    public static @NotNull UUID getShootWeaponUUID(@NotNull Projectile projectile) throws NullPointerException {
        return UUID.fromString(Objects.requireNonNull(projectile.getPersistentDataContainer().get(shootWeaponUUID, PersistentDataType.STRING)));
    }

}
