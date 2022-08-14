package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Cancellable;

public abstract class ProjectileShootCancelableEvent extends ProjectileShootEvent implements Cancellable {
    private boolean isCancelled = true;

    public ProjectileShootCancelableEvent(ProjectileShoot<?> projectileShoot) {
        super(projectileShoot);
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
