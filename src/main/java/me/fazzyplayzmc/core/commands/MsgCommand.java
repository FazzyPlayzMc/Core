package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    Core plugin;

    public MsgCommand(Core plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player player = (Player) sender;
        if(player.hasPermission("core.command.message")){
            if(args.length > 0){
                if(Bukkit.getOfflinePlayer(args[0]).getPlayer() != null){
                    Player messager = (Player) sender;
                    Player receiver = Bukkit.getOfflinePlayer(args[0]).getPlayer();
                    plugin.mM.setReplyTarget(messager, receiver);
                    args[0] = "";
                    String message = "";
                    for(int i = 0; i < args.length; i++){
                        message += " " + args[i];
                    }
                    messager.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Me" + ChatColor.DARK_GRAY + " -> " + ChatColor.GRAY + receiver.getName() + ChatColor.DARK_GRAY + "] " + message);
                    receiver.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + messager.getName() + ChatColor.DARK_GRAY + " -> " + ChatColor.GRAY + "Me" + ChatColor.DARK_GRAY + "] " + message);
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-online")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-format")));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
        return false;
    }
}
