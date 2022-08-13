package net.maidkleid.weaponapi.weaponlib;

import net.maidkleid.weaponapi.defaultweapons.Pistol;

import java.util.HashMap;

public enum WeaponEnum {
    PISTOL(new Pistol());
    public final Weapon CLASS;

    WeaponEnum(Weapon instance) {
        CLASS = instance;
    }

    private static final HashMap<Integer,Weapon> configWeaponMap = new HashMap<>();

    public static Weapon getWeapon(int customModelData) {
        int rangeID = (customModelData/1000);
        if(rangeID >= WeaponEnum.values().length) {
            return configWeaponMap.get(rangeID);
        }
        return WeaponEnum.values()[rangeID].CLASS;
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
