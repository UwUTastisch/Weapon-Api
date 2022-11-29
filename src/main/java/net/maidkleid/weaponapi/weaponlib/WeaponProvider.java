package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.WeaponAPI;
import net.maidkleid.weaponapi.defaultweapons.Pistol;
import net.maidkleid.weaponapi.defaultweapons.PumpGun;
import net.maidkleid.weaponapi.utils.WeaponItemLowLevelUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WeaponProvider {
    private static final HashMap<Integer,Weapon> configWeaponMap = new HashMap<>();
    private static final HashMap<String,Integer> configNameWeaponMap = new HashMap<>();
    private static final HashMap<UUID,WeaponInstance> weaponInstanceMap = new HashMap<>();

    public static final Pistol PISTOL = addConfigWeapon(new Pistol(), 0);
    public static final PumpGun PUMP_GUN = addConfigWeapon(new PumpGun(),1);
    public static Weapon getWeapon(int customModelData) {
        return configWeaponMap.get(getRangeID(customModelData));
    }

    public static Weapon getWeapon(String weaponName) {
        return configWeaponMap.get(configNameWeaponMap.get(weaponName));
    }

    public static int getWeaponID(String weaponName) {
        return configNameWeaponMap.get(weaponName);
    }

    /**
     * @return customModelData/1000
     */
    public static int getRangeID(int customModelData) {
        return (customModelData/1000);
    }

    public static int getLowestCustomModelDataID(int rangeID) {
        return rangeID*1000;
    }

    /**
     *
     * @param weapon could be any Weapon of Joice =)
     * @param rangeID not allowed to be smaller than 1000
     */
    public static <T extends Weapon> T addConfigWeapon(T weapon, int rangeID) {
        WeaponAPI.getPlugin(WeaponAPI.class).getLogger().info("Loading Weapon: " + weapon.getName() + ", rangeID: " + rangeID + ", customModelID: " + getLowestCustomModelDataID(rangeID));
        configWeaponMap.put(rangeID,weapon);
        configNameWeaponMap.put(weapon.getName(), rangeID);
        return weapon;
    }

    /**
     *
     * @return clone of the nameList :]
     */
    public static List<String> getAllWeaponNames() {
        return new ArrayList<>(configNameWeaponMap.keySet());
    }


    public static @Nullable WeaponInstance getWeaponInstance(ItemStack stack, Player player, int slot) {
        UUID uuid;
        try {
            uuid = WeaponItemLowLevelUtils.getUUID(stack.getItemMeta());
        } catch (Exception e) {
            return null;
        }
        WeaponInstance instance = weaponInstanceMap.get(uuid);
        //System.out.println(instance);
        if(instance != null) {
            instance.slot = slot;
            instance.player = player;

        } else {
            instance = getWeapon(stack.getItemMeta().getCustomModelData()).
                    getWeaponNewInstance(
                            player,
                            player.getInventory().getHeldItemSlot(),
                            stack
                    );
            weaponInstanceMap.put(instance.getUUID(),instance);
        }
        return instance;
    }
}