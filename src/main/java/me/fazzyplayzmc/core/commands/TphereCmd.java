package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCmd implements CommandExecutor {

    private Core plugin;

    public TphereCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 1){
            if (p.hasPermission("core.command.tphere")){
                if (Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    target.teleport(p.getLocation());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tphere-name-color")) + target.getName() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tphere-msg")));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tphere-target")) + sender.getName());
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                }
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        } else if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tphere-format")));
        }
        return true;
    }
}
