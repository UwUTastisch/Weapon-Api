package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ProjectileShootUpdateEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final ProjectileShoot<?> projectileShoot;

    public ProjectileShootUpdateEvent(ProjectileShoot<?> projectileShoot) {
        this.projectileShoot = projectileShoot;
    }


    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public ProjectileShoot<?> getProjectileShoot() {
        return projectileShoot;
    }

}
