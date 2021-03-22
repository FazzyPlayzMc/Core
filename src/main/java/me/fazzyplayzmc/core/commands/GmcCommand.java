package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcCommand implements CommandExecutor {

    private Core plugin;

    public GmcCommand(Core plugin) {
        this.plugin = plugin;
    }

    // Command for changing Gamemode to creative for yourself and others
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
            Player player = (Player) sender;
            if(args.length == 0){
                gmcMethod(player);
            } else if (args.length == 1){
                if(player.hasPermission("core.command.gamemode.others")){
                    Player target = Bukkit.getPlayer(args[0]);
                    gmcMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
                }
            }
        return true;
    }

    // Method for changing gamemode
    private void gmcMethod(Player player) {
        if (player.hasPermission("core.command.gamemode.creative")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gamemode-creative")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
    }

}
