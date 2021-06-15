package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class TpallCmd implements CommandExecutor {

    private Core plugin;

    public TpallCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (p.hasPermission("core.command.tpall")){
            if (Bukkit.getServer().getOnlinePlayers().size() == 1){
                sender.sendMessage(Core.color(plugin.getConfig().getString("tpall-no-players")));
            } else {
                Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
                if (onlinePlayers.size() > 1){
                    for (Player pa : Bukkit.getServer().getOnlinePlayers()){
                        pa.teleport(p.getLocation());
                        pa.sendMessage(Core.color(plugin.getConfig().getString("target-tpall")) + p.getName());
                    }
                }
            p.sendMessage(Core.color(plugin.getConfig().getString("playernum-color")) + (onlinePlayers.size() - 1) + Core.color(plugin.getConfig().getString("tpall-msg")));
            }
        } else {
            p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
        }
        return true;
    }
}
