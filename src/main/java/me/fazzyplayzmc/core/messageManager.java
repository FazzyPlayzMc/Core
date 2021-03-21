package me.fazzyplayzmc.core;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class messageManager {

    private Core plugin;

    HashMap<Player,Player> conversations = new HashMap<Player, Player>();

    public messageManager(Core plugin) {
        this.plugin = plugin;
    }

    public void setReplyTarget(Player messager, Player receiver){
        conversations.put(messager, receiver);
        conversations.put(receiver, messager);
    }

    public Player getReplyTarget(Player messager){
        return conversations.get(messager);
    }

}
