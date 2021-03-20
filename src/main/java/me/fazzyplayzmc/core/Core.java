package me.fazzyplayzmc.core;

import me.fazzyplayzmc.core.commands.flyCommand;
import me.fazzyplayzmc.core.commands.gmcCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enable Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Set commands here
        getCommand("fly").setExecutor(new flyCommand(this));
        getCommand("gmc").setExecutor(new gmcCommand(this));
    }
}
