package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
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

        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
            if(args.length == 0){
                if(p.hasPermission("core.command.fly")){
                    p.setAllowFlight(!p.getAllowFlight());
                    p.sendMessage(Core.color(plugin.getConfig().getString("fly-" + p.getAllowFlight())));
                } else {
                    p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
                }
            } else if (args.length == 1){
                if (p.hasPermission("core.command.fly.others")){
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setAllowFlight(!p.getAllowFlight());
                    target.sendMessage(Core.color(plugin.getConfig().getString("fly-" + p.getAllowFlight())));
                } else {
                    p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
                }
            }

        return true;
    }
}
