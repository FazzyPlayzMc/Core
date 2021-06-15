package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
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
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 1) {
            if (p.hasPermission("core.command.feed.others")) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFoodLevel(20);
                    target.setSaturation(5);
                    target.sendMessage(Core.color(plugin.getConfig().getString("target-fed") + sender.getName()));
                    sender.sendMessage(Core.color(plugin.getConfig().getString("fed-others") + target.getName()));
                } else {
                    sender.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                }
                return true;
            }
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }

        if (args.length == 0) {
            if (p.hasPermission("core.command.feed")) {
                p.setFoodLevel(20);
                p.setSaturation(5);
                sender.sendMessage(Core.color(plugin.getConfig().getString("fed-self")));
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
