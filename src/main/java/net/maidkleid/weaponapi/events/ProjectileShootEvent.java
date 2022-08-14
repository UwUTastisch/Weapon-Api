package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class ProjectileShootEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
    private final ProjectileShoot<?> projectileShoot;

    public ProjectileShootEvent(ProjectileShoot<?> projectileShoot) {
        this.projectileShoot = projectileShoot;
    }

    public ProjectileShoot<?> getProjectileShoot() {
        return projectileShoot;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
