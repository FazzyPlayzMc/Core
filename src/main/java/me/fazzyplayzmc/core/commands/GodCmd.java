package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCmd implements CommandExecutor {
    private Core plugin;

    public GodCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 0){
            if(p.hasPermission("core.command.god")){
                if (plugin.hasGodPlayers()){
                    if (plugin.getGodPlayers().contains(p)){
                        plugin.removeGodPlayer(p);
                        p.sendMessage(Core.color(plugin.getConfig().getString("god-false")));
                    }
                } else {
                    plugin.addGodPlayer(p);
                    p.sendMessage(Core.color(plugin.getConfig().getString("god-true")));
                }
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        } else if (args.length == 1){
            if (p.hasPermission("core.command.god.others")){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player t = Bukkit.getPlayer(args[0]);
                    if (plugin.hasGodPlayers()){
                        if (plugin.getGodPlayers().contains(t)){
                            plugin.removeGodPlayer(t);
                            t.sendMessage(Core.color(plugin.getConfig().getString("god-false-byothers") + sender.getName()));
                            sender.sendMessage(Core.color(plugin.getConfig().getString("god-false-other") + t.getName()));
                        }
                    } else {
                        plugin.addGodPlayer(t);
                        t.sendMessage(Core.color(plugin.getConfig().getString("god-true-byothers") + sender.getName()));
                        sender.sendMessage(Core.color(plugin.getConfig().getString("god-true-other") + t.getName()));
                    }
                } else {
                    sender.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                }
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
