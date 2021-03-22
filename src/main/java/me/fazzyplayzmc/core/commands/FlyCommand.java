package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private Core plugin;

    public FlyCommand(Core plugin) {
        this.plugin = plugin;
    }

    // Command for changing toggling fly for yourself and others
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
            Player player = (Player) sender;
            if(args.length == 0){
                flyMethod(player);
            } else if (args.length == 1){
                if (player.hasPermission("core.command.fly.others")){
                    Player target = Bukkit.getPlayer(args[0]);
                    flyMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }

        return true;
    }

    // Method for toggling fly
    private void flyMethod(Player player){
        if(player.hasPermission("core.command.fly")){
            if (player.getAllowFlight() == true) {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-false")));
            } else if (!player.getAllowFlight() == true) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fly-true")));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
    }
}
