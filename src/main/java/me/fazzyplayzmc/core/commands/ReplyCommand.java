package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    Core plugin;

    public ReplyCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (p.hasPermission("core.command.reply")){
            if (args.length > 0){
                Player messager = (Player) sender;
                if(plugin.messageManager.getReplyTarget(messager) == null){
                    messager.sendMessage(Core.color(plugin.getConfig().getString("no-conversation")));
                    return true;
                }
                Player receiver = plugin.messageManager.getReplyTarget(messager);
                StringBuilder message = new StringBuilder();
                for (String arg : args){
                    message.append(" ").append(arg);
                }
                messager.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Me" + ChatColor.DARK_GRAY + " -> " + ChatColor.GRAY + receiver.getName() + ChatColor.DARK_GRAY + "] " + message);
                receiver.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + messager.getName() + ChatColor.DARK_GRAY + " -> " + ChatColor.GRAY + "Me" + ChatColor.DARK_GRAY + "] " + message);
                return true;
            } else {
                sender.sendMessage(Core.color(plugin.getConfig().getString("reply-format")));
            }
        } else {
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }
        return false;
    }
}
