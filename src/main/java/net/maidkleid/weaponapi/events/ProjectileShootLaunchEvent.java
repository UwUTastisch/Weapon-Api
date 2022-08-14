package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ProjectileShootLaunchEvent extends ProjectileShootCancelableEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public ProjectileShootLaunchEvent(ProjectileShoot<?> projectileShoot) {
        super(projectileShoot);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
