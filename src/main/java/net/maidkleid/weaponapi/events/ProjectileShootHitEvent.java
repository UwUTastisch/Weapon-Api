package net.maidkleid.weaponapi.events;

import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ProjectileShootHitEvent extends ProjectileHitEvent implements ProjectileShootProvider {

    private final ProjectileShoot<?> projectileShoot;

    public ProjectileShootHitEvent(@NotNull ProjectileShoot<?> projectileShoot) {
        super(Objects.requireNonNull(projectileShoot.getProjectile()));
        this.projectileShoot = projectileShoot;
    }

    public ProjectileShootHitEvent(@NotNull ProjectileShoot<?> projectileShoot, @Nullable Entity hitEntity) {
        super(Objects.requireNonNull(projectileShoot.getProjectile()), hitEntity);
        this.projectileShoot = projectileShoot;
    }

    public ProjectileShootHitEvent(@NotNull ProjectileShoot<?> projectileShoot, @Nullable Block hitBlock) {
        super(Objects.requireNonNull(projectileShoot.getProjectile()), hitBlock);
        this.projectileShoot = projectileShoot;
    }

    public ProjectileShootHitEvent(@NotNull ProjectileShoot<?> projectileShoot, @Nullable Entity hitEntity, @Nullable Block hitBlock) {
        super(Objects.requireNonNull(projectileShoot.getProjectile()), hitEntity, hitBlock);
        this.projectileShoot = projectileShoot;
    }

    public ProjectileShootHitEvent(@NotNull ProjectileShoot<?> projectileShoot, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable BlockFace hitFace) {
        super(Objects.requireNonNull(projectileShoot.getProjectile()), hitEntity, hitBlock, hitFace);
        this.projectileShoot = projectileShoot;
    }

    @Override
    public ProjectileShoot<?> getProjectile() {
        return projectileShoot;
    }

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
