package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmcCommand implements CommandExecutor {

    private Core plugin;

    public gmcCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
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
        }
        return true;
    }

    private void gmcMethod(Player player) {
        if (player.hasPermission("core.command.gamemode.creative")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gamemode-creative")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
    }

}
