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
        getCommand("fspeed").setExecutor(new FspeedCmd(this));
        getCommand("gmc").setExecutor(new GmcCommand(this));
        getCommand("gms").setExecutor(new GmsCommand(this));
        getCommand("gma").setExecutor(new GmaCommand(this));
        getCommand("gmsp").setExecutor(new GmspCommand(this));
        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("r").setExecutor(new ReplyCommand(this));
        getCommand("discord").setExecutor(new DiscordCommand(this));
        getCommand("website").setExecutor(new WebCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("kill").setExecutor(new KillCmd(this));
        getCommand("suicide").setExecutor(new SuicideCmd(this));
        getCommand("heal").setExecutor(new HealCmd(this));
        getCommand("feed").setExecutor(new FeedCmd(this));
        getCommand("smite").setExecutor(new SmiteCmd(this));
        getCommand("burn").setExecutor(new BurnCmd(this));
        getCommand("clearinventory").setExecutor(new ClearinvCmd(this));
        getCommand("tp").setExecutor(new TpCmd(this));
        getCommand("tphere").setExecutor(new TphereCmd(this));
        getCommand("tpall").setExecutor(new TpallCmd(this));
        getCommand("wspeed").setExecutor(new WspeedCmd(this));
        mM = new messageManager(this);
    }
}
