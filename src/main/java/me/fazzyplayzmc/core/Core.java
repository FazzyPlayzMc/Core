package me.fazzyplayzmc.core;

import me.fazzyplayzmc.core.commands.FlyCommand;
import me.fazzyplayzmc.core.commands.GmcCommand;
import me.fazzyplayzmc.core.commands.GmsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enable Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Set commands here
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("gmc").setExecutor(new GmcCommand(this));
        getCommand("gms").setExecutor(new GmsCommand(this));
    }
}
