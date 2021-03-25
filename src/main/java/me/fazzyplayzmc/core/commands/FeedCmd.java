package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCmd implements CommandExecutor {

    private Core plugin;

    public FeedCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 1) {
            if (p.hasPermission("core.command.feed.others")) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFoodLevel(20);
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("target-fed") + sender.getName()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fed-others") + target.getName()));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                }
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));

        }

        if (args.length == 0) {
            if (p.hasPermission("core.command.feed")) {
                p.setFoodLevel(20);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fed-self")));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
