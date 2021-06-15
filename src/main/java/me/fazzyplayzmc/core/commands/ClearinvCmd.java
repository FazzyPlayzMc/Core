package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearinvCmd implements CommandExecutor{

    private Core plugin;

    public ClearinvCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length >= 1){
            if (p.hasPermission("core.command.clearinv.others")){
                if (Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    target.getInventory().clear();
                    target.getInventory().setArmorContents(null);
                    target.getInventory().setItemInOffHand(null);
                    target.sendMessage(Core.color(plugin.getConfig().getString("target-ci") + sender.getName()));
                    sender.sendMessage(Core.color(plugin.getConfig().getString("sender-ci") + target.getName()));
                } else {
                    sender.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                }
                return true;
            }
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }

        if (args.length <= 0){
            if (p.hasPermission("core.command.clearinv")){
                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                p.getInventory().setItemInOffHand(null);
                sender.sendMessage(Core.color(plugin.getConfig().getString("self-ci")));
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
