package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class ProjectileShootCancelableEvent extends ProjectileShootEvent implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
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

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
