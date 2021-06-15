package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCmd implements CommandExecutor {

    private Core plugin;

    public HealCmd(Core plugin){
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (p.hasPermission("core.command.heal.others")){
            if (args.length == 1){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    target.sendMessage(Core.color(plugin.getConfig().getString("target-healed") + sender.getName()));
                    sender.sendMessage(Core.color(plugin.getConfig().getString("healed-others") + target.getName()));
                } else {
                    sender.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                }
            }
        } else {
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }

        if (p.hasPermission("core.command.heal")){
            if (args.length == 0){
                p.setHealth(20);
                p.setFoodLevel(20);
                sender.sendMessage(Core.color(plugin.getConfig().getString("healed-self")));
            }
        } else {
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }

        return true;
    }

}
