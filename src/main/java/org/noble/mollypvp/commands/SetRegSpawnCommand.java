package org.noble.mollypvp.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.noble.mollypvp.MollyPVP;

public class SetRegSpawnCommand implements CommandExecutor {

    private MollyPVP plugin;

    public SetRegSpawnCommand(MollyPVP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players!");
            return true;
        }

        if (args.length >= 1) {
            String region = args[0];
            Player player = (Player) sender;
            Location loc = player.getLocation();
            String locationString = loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
            plugin.getPluginConfig().set("regions." + region + ".respawn", locationString);
            plugin.saveConfig();
            sender.sendMessage("Respawn location for region " + region + " set to your current location.");
            return true;
        }
        return false;
    }
}
