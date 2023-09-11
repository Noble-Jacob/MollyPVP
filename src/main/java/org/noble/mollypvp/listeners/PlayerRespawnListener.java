package org.noble.mollypvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.noble.mollypvp.utilities.RegionUtility;
import org.noble.mollypvp.MollyPVP;

public class PlayerRespawnListener implements Listener {

    private MollyPVP plugin;

    public PlayerRespawnListener(MollyPVP plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String regionName = RegionUtility.getRegionNameFromLocation(player.getLocation());

        if (regionName != null && plugin.getPluginConfig().contains("regions." + regionName)) {
            String respawnLocation = plugin.getPluginConfig().getString("regions." + regionName + ".respawn");
            if (respawnLocation != null) {
                String[] locParts = respawnLocation.split(",");
                double x = Double.parseDouble(locParts[0]);
                double y = Double.parseDouble(locParts[1]);
                double z = Double.parseDouble(locParts[2]);
                float yaw = Float.parseFloat(locParts[3]);
                float pitch = Float.parseFloat(locParts[4]);

                Location respawnLoc = new Location(player.getWorld(), x, y, z, yaw, pitch);
                event.setRespawnLocation(respawnLoc);
                plugin.getLogger().info("Respawning player " + player.getName() + " to " + respawnLoc.toString());
            } else {
                event.setRespawnLocation(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        }
    }
}
