package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WspeedCmd implements CommandExecutor {

    private Core plugin;

    public WspeedCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("wspeed-format")));
        } else if (args.length == 1){
            if (p.hasPermission("core.command.wspeed")){
                float a = Float.valueOf(args[0]);
                if (a <= 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-number")));
                } else if (a > 0) {
                    try {
                        p.setWalkSpeed(a);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("set-wspeed")) + a);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-number")));
                    }
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
