package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCmd implements CommandExecutor {

    private Core plugin;

    public TpCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 1) {
            if (p.hasPermission("core.command.tp")){
                if (Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    p.teleport(target.getLocation());
                    sender.sendMessage(Core.color(plugin.getConfig().getString("tp-msg") + target.getName()));
                } else {
                    sender.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                }
                return true;
            }
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        } else if (args.length == 0){
            sender.sendMessage(Core.color(plugin.getConfig().getString("tp-format")));
        }
        return true;
    }
}
