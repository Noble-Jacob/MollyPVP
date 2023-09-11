package org.noble.mollypvp.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitListener implements Listener {

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damaged = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            // Display the particle effect at the damaged player's location
            damaged.getWorld().spawnParticle(Particle.CRIT, damaged.getLocation().add(0, 1, 0), 10); // Adding 1 to the y-coordinate to make it appear slightly above the ground

            // If you want to show a particle effect for the damager as well
            // damager.getWorld().spawnParticle(Particle.CRIT, damager.getLocation().add(0, 1, 0), 10);
        }
    }
}
