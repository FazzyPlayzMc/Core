package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    private Core plugin;

    public DiscordCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
            if(p.hasPermission("core.command.discord")){
                sender.sendMessage(Core.color(plugin.getConfig().getString("discord-output")));
            } else {
                sender.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        return true;
    }
}
