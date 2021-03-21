package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmsCommand implements CommandExecutor {
    private Core plugin;

    public GmsCommand(Core plugin) {
        this.plugin = plugin;
    }

    // Command for changing Gamemode to survival for yourself and others
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
        } else {
            Player player = (Player) sender;
            if(args.length == 0){
                gmsMethod(player);
            } else if (args.length == 1){
                if(player.hasPermission("core.command.gamemode.others")){
                    Player target = Bukkit.getPlayer(args[0]);
                    gmsMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }
        }
        return true;
    }

    // Method for changing gamemode
    private void gmsMethod(Player player) {
        if (player.hasPermission("core.command.gamemode.survival")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gamemode-survival")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
    }
}
