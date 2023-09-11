package org.noble.mollypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.noble.mollypvp.MollyPVP;

public class MollyPVPReloadCommand implements CommandExecutor {

    private MollyPVP plugin;

    public MollyPVPReloadCommand(MollyPVP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("mollypvp.reload")) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }
        plugin.reloadConfig();
        sender.sendMessage("MollyPVP configuration reloaded successfully!");
        return true;
    }
}
