package org.noble.mollypvp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.noble.mollypvp.listeners.PlayerDeathListener;
import org.noble.mollypvp.listeners.PlayerHitListener;
import org.noble.mollypvp.listeners.PlayerRespawnListener;
import org.noble.mollypvp.commands.SetRegSpawnCommand;
import org.noble.mollypvp.commands.MollyPVPReloadCommand;

public class MollyPVP extends JavaPlugin {

    private FileConfiguration config;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        // Register events
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHitListener(), this); // Add this line

        // Register commands
        this.getCommand("setregspawn").setExecutor(new SetRegSpawnCommand(this));
        this.getCommand("mollypvp-reload").setExecutor(new MollyPVPReloadCommand(this));
    }


    @Override
    public void onDisable() {
        saveConfig();
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }

}
