package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RepairCmd implements CommandExecutor {

    private Core plugin;

    public RepairCmd(Core plugin){
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }
        if (args.length == 0){
            if (!(p.hasPermission("core.command.repair"))) {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
                return true;
            }
            if (p.getItemInHand() == null){
                sender.sendMessage(Core.color(plugin.getConfig().getString("no-item")));
                return true;
            }
            if (p.getItemInHand().getType() == Material.AIR
                    || p.getItemInHand().getDurability() == 0
                    || p.getItemInHand().getType().getMaxDurability() <= 0
                    || p.getItemInHand().getType().isBlock()
                    || p.getItemInHand().getType().isEdible()){
                p.sendMessage(Core.color(plugin.getConfig().getString("invalid-item")));
                return true;
            }
            p.getItemInHand().setDurability((short) 0);
            p.updateInventory();
            sender.sendMessage(Core.color(plugin.getConfig().getString("repaired-item")));
        }
        if (args.length == 1){
            if (!(p.hasPermission("core.command.repair.others"))){
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
                return true;
            }
            if (Bukkit.getPlayerExact(args[0]) == null){
                p.sendMessage(Core.color(plugin.getConfig().getString("player-not-found")));
                return true;
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (t.getItemInHand() == null){
                sender.sendMessage(Core.color(plugin.getConfig().getString("no-item")));
                return true;
            }
            if (t.getItemInHand().getType() == Material.AIR
                    || t.getItemInHand().getDurability() == 0
                    || t.getItemInHand().getType().getMaxDurability() <= 0
                    || t.getItemInHand().getType().isBlock()
                    || t.getItemInHand().getType().isEdible()){
                p.sendMessage(Core.color(plugin.getConfig().getString("invalid-item")));
                return true;
            }
            t.getItemInHand().setDurability((short) 0);
            t.updateInventory();
            sender.sendMessage(Core.color(plugin.getConfig().getString("repaired-item")));
        }
        return true;
    }
}
