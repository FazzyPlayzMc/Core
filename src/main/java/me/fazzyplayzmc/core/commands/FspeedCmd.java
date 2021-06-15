package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FspeedCmd implements CommandExecutor {

    private Core plugin;

    public FspeedCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 0){
            sender.sendMessage(Core.color(plugin.getConfig().getString("fspeed-format")));
        } else if (args.length == 1){
            if (p.hasPermission("core.command.fspeed")){
                float a = Float.parseFloat(args[0]);
                if (a <= 0){
                    sender.sendMessage(Core.color(plugin.getConfig().getString("invalid-number")));
                } else if (a > 0) {
                    try {
                        p.setFlySpeed(a);
                        p.sendMessage(Core.color(plugin.getConfig().getString("set-fspeed")) + a);
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
