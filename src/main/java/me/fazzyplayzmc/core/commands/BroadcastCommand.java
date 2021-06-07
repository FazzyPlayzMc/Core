package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    private Core plugin;

    public BroadcastCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player = (Player) sender;
        if (!(player instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (player.hasPermission("core.command.broadcast")){
            if (args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("broadcast-format")));
            } else if (args.length > 0){
                String msg = "";
                for (String a : args){
                    msg += a+" ";
                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("broadcast-prefix")) + FORMAT(msg));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
        return true;
    }

    private static final String FORMAT(String text){
        return text.replace("&", "§");
    }
}
