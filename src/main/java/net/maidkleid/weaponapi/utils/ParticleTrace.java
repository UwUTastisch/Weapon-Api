package net.maidkleid.weaponapi.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleTrace {
    public static void spawnParticleTrace(Location a, Location b, Particle p, double density) {
        World world = a.getWorld();
        Location l = a.clone();
        Vector v = b.subtract(a).toVector();
        double length = v.length();
        v.normalize().multiply(density);

        for (double i = 0; i < length; i += density) world.spawnParticle(p,l.add(v), 0,0,0,0);
    }
}
