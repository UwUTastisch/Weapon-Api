package net.maidkleid.weaponapi.events;

import com.google.common.base.Function;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ProjectileShootDamageEvent extends EntityDamageEvent {

    ProjectileShoot<?> shoot;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public ProjectileShootDamageEvent(@NotNull Entity damagee, ProjectileShoot<?> shoot) {
        super(damagee, DamageCause.PROJECTILE, shoot.weaponInstance.getBulletDamage());
        this.shoot = shoot;
    }

    public ProjectileShootDamageEvent(@NotNull Entity damagee, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions, ProjectileShoot<?> shoot) {
        super(damagee, DamageCause.PROJECTILE, modifiers, modifierFunctions);
        this.shoot = shoot;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
