package net.theallay.weaponapi.weaponlib;

import net.theallay.weaponapi.defaultweapons.Pistol;

import java.util.HashMap;

public enum WeaponLib {
    PISTOL(new Pistol());
    public final Weapon INSTANCE;

    WeaponLib(Weapon instance) {
        INSTANCE = instance;
    }

    private static final HashMap<Integer,Weapon> configWeaponMap = new HashMap<>();

    public static Weapon getWeapon(int customModelData) {
        int rangeID = (customModelData/1000);
        if(rangeID >= WeaponLib.values().length) {
            return configWeaponMap.get(rangeID);
        }
        return WeaponLib.values()[rangeID].INSTANCE;
    }

    /**
     *
     * @param weapon could be any Weapon of Joice =)
     * @param customModelData not allowed to be smaller than 1000000
     */
    private static void addConfigWeapon(Weapon weapon, int customModelData) {
        int rangeID = (customModelData/1000);
        configWeaponMap.put(rangeID,weapon);
    }
}
