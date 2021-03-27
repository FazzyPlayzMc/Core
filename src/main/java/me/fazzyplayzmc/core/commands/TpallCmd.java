package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpallCmd implements CommandExecutor {

    private Core plugin;

    public TpallCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("must-be-player")));
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("core.command.tpall")){
            if (Bukkit.getServer().getOnlinePlayers().size() == 1){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpall-no-players")));
            } else if (Bukkit.getServer().getOnlinePlayers().size() > 1){
                int numOfPlayers = 0;
                for (Player pa : Bukkit.getServer().getOnlinePlayers()){
                    pa.teleport(p.getLocation());
                    numOfPlayers++;
                    pa.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("target-tpall")) + p.getName());
                }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("playernum-color")) + (numOfPlayers - 1) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tpall-msg")));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
        }
        return true;
    }
}
