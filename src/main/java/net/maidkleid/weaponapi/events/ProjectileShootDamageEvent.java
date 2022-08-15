package net.maidkleid.weaponapi.events;

import com.google.common.base.Function;
import net.maidkleid.weaponapi.weaponlib.shoots.ProjectileShoot;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ProjectileShootDamageEvent extends EntityDamageEvent implements ProjectileShootProvider {

    private final ProjectileShoot<?> projectileShoot;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private static Vector knock;

    public ProjectileShootDamageEvent(@NotNull Entity damagee, ProjectileShoot<?> projectileShoot) {
        super(damagee, DamageCause.PROJECTILE, projectileShoot.getWeaponInstance().getBulletDamage());
        this.projectileShoot = projectileShoot;
        knock = projectileShoot.getVelocity().normalize().multiply(0.5);
    }

    public ProjectileShootDamageEvent(@NotNull Entity damagee, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions, ProjectileShoot<?> projectileShoot) {
        super(damagee, DamageCause.PROJECTILE, modifiers, modifierFunctions);
        this.projectileShoot = projectileShoot;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public ProjectileShoot<?> getProjectile() {
        return projectileShoot;
    }

    public Vector getKnockBack() {
        return knock.clone();
    }

    public void setKnockBack(Vector knock) {
        this.knock = knock;
    }


}
