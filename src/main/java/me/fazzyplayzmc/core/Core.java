package me.fazzyplayzmc.core;

import me.fazzyplayzmc.core.commands.*;
import me.fazzyplayzmc.core.listener.PlayerChatListener;
import me.fazzyplayzmc.core.listener.PlayerDamageListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Core extends JavaPlugin {

    public MessageManager messageManager;
    private List<Player> GodPlayers = new ArrayList<>();

    public MessageManager getMessageManager(){
        return messageManager;
    }

    @Override
    public void onEnable() {
        // Enable Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Set commands here
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("fspeed").setExecutor(new FspeedCmd(this));
        GamemodeCmd gamemodeCmd = new GamemodeCmd(this);
        getCommand("gmc").setExecutor(gamemodeCmd);
        getCommand("gms").setExecutor(gamemodeCmd);
        getCommand("gmsp").setExecutor(gamemodeCmd);
        getCommand("gma").setExecutor(gamemodeCmd);
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
        getCommand("god").setExecutor(new GodCmd(this));
        getCommand("nick").setExecutor(new NickCmd(this));
        getCommand("repair").setExecutor(new RepairCmd(this));
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        messageManager = new MessageManager(this);

        // Registering Listener
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);

    }

    public void addGodPlayer(Player player){
        GodPlayers.add(player);
    }

    public void removeGodPlayer(Player player){
        GodPlayers.remove(player);
    }

    public List<Player> getGodPlayers(){
        return GodPlayers;
    }

    public boolean hasGodPlayers(){
        return !GodPlayers.isEmpty();
    }

    public static String color(String uncolored){
        return ChatColor.translateAlternateColorCodes('&', uncolored);
    }
}
