package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ProjectileShootLaunchEvent extends Event implements Cancellable, ProjectileShootProvider {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final ProjectileShoot<?> projectileShoot;

    public ProjectileShootLaunchEvent(ProjectileShoot<?> projectileShoot) {
        this.projectileShoot = projectileShoot;
    }

    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    private boolean isCanceled = false;

    @Override
    public ProjectileShoot<?> getProjectile() {
        return projectileShoot;
    }

    @Override
    public boolean isCancelled() {
        return isCanceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCanceled = cancel;
    }
}
