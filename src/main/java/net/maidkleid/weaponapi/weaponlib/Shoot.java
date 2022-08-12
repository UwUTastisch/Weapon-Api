package net.maidkleid.weaponapi.weaponlib;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Shoot {

    public final WeaponInstance weaponInstance;
    private final Location startPosition;
    private final Location tickPosition;
    private final Vector velocity;

    public Shoot(WeaponInstance weaponInstance, Location startPosition, Location tickPosition, Vector velocity) {
        this.weaponInstance = weaponInstance;
        this.startPosition = startPosition;
        this.tickPosition = startPosition.clone();
        this.velocity = velocity;
    }

    public Location getStartPosition() {
        return startPosition.clone();
    }

    public Location getTickPosition() {
        return tickPosition.clone();
    }

    public Vector getVelocity() {
        return velocity.clone();
    }

}
