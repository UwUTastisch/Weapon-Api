package net.maidkleid.weaponapi.weaponlib.shoots;

import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.Location;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class Shoot {

    protected final WeaponInstance weaponInstance;
    protected final Location startPosition;
    protected Location tickPosition;

    protected ArrayList<Vector> trace;
    protected final Vector velocity;
    public Shoot(WeaponInstance weaponInstance, Location startPosition, Vector velocity) {
        trace = new ArrayList<>();
        this.weaponInstance = weaponInstance;
        this.startPosition = startPosition;
        this.tickPosition = startPosition.clone();
        trace.add(tickPosition.toVector());
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

    protected abstract boolean doUpdateTick();

    public WeaponInstance getWeaponInstance() {
        return weaponInstance;
    }

    protected boolean addTraceLocation(Location l) {
        boolean equals = l.toVector().equals(trace.get(trace.size() - 1));
        trace.add(l.toVector());
        return !equals;
    }

    protected boolean addTickPositionToTrace() {
        return addTraceLocation(tickPosition);
    }

    public void callHitEvent(ProjectileHitEvent event) {

    }

    public List<Vector> getTrace() {
        return List.copyOf(trace);
    }
}
