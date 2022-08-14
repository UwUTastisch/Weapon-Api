package net.maidkleid.weaponapi.weaponlib.shoots;

import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class Shoot {

    public final WeaponInstance weaponInstance;
    protected final Location startPosition;
    protected Location tickPosition;
    protected final Vector velocity;

    public Shoot(WeaponInstance weaponInstance, Location startPosition, Vector velocity) {
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

    abstract protected boolean doUpdateTick() ;

}
