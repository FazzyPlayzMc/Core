package me.fazzyplayzmc.core;

import me.fazzyplayzmc.core.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    public messageManager mM;

    @Override
    public void onEnable() {
        // Enable Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Set commands here
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("gmc").setExecutor(new GmcCommand(this));
        getCommand("gms").setExecutor(new GmsCommand(this));
        getCommand("gma").setExecutor(new GmaCommand(this));
        getCommand("gmsp").setExecutor(new GmspCommand(this));
        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("r").setExecutor(new ReplyCommand(this));
        getCommand("discord").setExecutor(new DiscordCommand(this));
        getCommand("website").setExecutor(new WebCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        mM = new messageManager(this);
    }
}
