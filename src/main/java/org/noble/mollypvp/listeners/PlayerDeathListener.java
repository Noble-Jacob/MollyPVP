package org.noble.mollypvp.listeners;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import org.noble.mollypvp.MollyPVP;
import org.bukkit.event.EventPriority;
import org.noble.mollypvp.utilities.RegionUtility;


public class PlayerDeathListener implements Listener {

    private MollyPVP plugin;

    public PlayerDeathListener(MollyPVP plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        String regionName = RegionUtility.getRegionNameFromLocation(victim.getLocation());


        if (regionName != null) {
            Player killer = victim.getKiller();
            if (killer != null) {
                String customMessage = plugin.getConfig().getString("custom-death-message");
                customMessage = ChatColor.translateAlternateColorCodes('&', customMessage);
                customMessage = customMessage.replace("%killer%", killer.getName()).replace("%victim%", victim.getName());
                event.setDeathMessage(customMessage);
            } else {
                event.setDeathMessage("§e" + victim.getName() + " died in the battleground!");
            }


            event.setKeepInventory(true);
            event.getDrops().clear();
            event.setDroppedExp(0);

            plugin.getLogger().info("Player " + victim.getName() + " died in region " + regionName + ". Keeping inventory.");
        }
    }
}
