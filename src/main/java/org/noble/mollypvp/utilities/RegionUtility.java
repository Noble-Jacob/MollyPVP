package org.noble.mollypvp.utilities;

import org.bukkit.Location;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionUtility {

    public static String getRegionNameFromLocation(Location location) {
        com.sk89q.worldedit.world.World worldEditWorld = BukkitAdapter.adapt(location.getWorld());
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(worldEditWorld);
        ApplicableRegionSet regions = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(location));

        for (ProtectedRegion region : regions) {
            if (region != null) {
                return region.getId();
            }
        }
        return null;
    }
}
