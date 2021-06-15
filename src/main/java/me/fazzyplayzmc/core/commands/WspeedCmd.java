package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WspeedCmd implements CommandExecutor {

    private Core plugin;

    public WspeedCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 0){
            sender.sendMessage(Core.color(plugin.getConfig().getString("wspeed-format")));
        } else if (args.length == 1){
            if (p.hasPermission("core.command.wspeed")){
                float a = Float.parseFloat(args[0]);
                if (a <= 0){
                    sender.sendMessage(Core.color(plugin.getConfig().getString("invalid-number")));
                } else if (a > 0) {
                    try {
                        p.setWalkSpeed(a);
                        p.sendMessage(Core.color(plugin.getConfig().getString("set-wspeed")) + a);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(Core.color(plugin.getConfig().getString("invalid-number")));
                    }
                }
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}
