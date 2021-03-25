package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteCmd implements CommandExecutor {

    private Core plugin;

    public SmiteCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player p = (Player) sender;
        Location loc = p.getLocation();
        if (p.hasPermission("core.command.smite")){
            if (args.length >= 1){
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.getWorld().strikeLightning(loc);
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                }
            } else if (args.length <= 0){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("smite-format")));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
        return true;
    }
}
