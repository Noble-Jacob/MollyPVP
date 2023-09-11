package org.noble.mollypvp.utilities;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.noble.mollypvp.MollyPVP;

public class ConfigUtility {

    private final MollyPVP plugin;

    public ConfigUtility(MollyPVP plugin) {
        this.plugin = plugin;
    }

    /**
     * Retrieves the respawn location for a given region from the config.
     *
     * @param regionName Name of the region.
     * @return Location object of the respawn location. Returns null if not found.
     */
    public Location getRespawnLocationForRegion(String regionName) {
        String respawnLocationStr = plugin.getPluginConfig().getString("regions." + regionName + ".respawn");
        if (respawnLocationStr == null) return null;

        String[] locParts = respawnLocationStr.split(",");
        double x = Double.parseDouble(locParts[0]);
        double y = Double.parseDouble(locParts[1]);
        double z = Double.parseDouble(locParts[2]);
        float yaw = Float.parseFloat(locParts[3]);
        float pitch = Float.parseFloat(locParts[4]);

        return new Location(plugin.getServer().getWorlds().get(0), x, y, z, yaw, pitch);
    }

    /**
     * Saves a respawn location for a given region into the config.
     *
     * @param regionName Name of the region.
     * @param location Location object of the respawn location.
     */
    public void setRespawnLocationForRegion(String regionName, Location location) {
        String locationString = location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
        plugin.getPluginConfig().set("regions." + regionName + ".respawn", locationString);
        plugin.saveConfig();
    }
}
