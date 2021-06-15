package me.fazzyplayzmc.core.commands;

import me.fazzyplayzmc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCmd implements CommandExecutor {

    private Core plugin;

    public GamemodeCmd(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)){
            sender.sendMessage(Core.color(plugin.getConfig().getString("must-be-player")));
            return true;
        }

        GameMode gameMode = switch (command.getName().toLowerCase()){
            case "gmc" -> GameMode.CREATIVE;
            case "gms" -> GameMode.SURVIVAL;
            case "gmsp" -> GameMode.SPECTATOR;
            case "gma" -> GameMode.ADVENTURE;
            default -> null;
        };

        if (args.length == 0){
            if (p.hasPermission("core.command.gamemode." + gameMode.name().toLowerCase())){
                p.setGameMode(gameMode);
                p.sendMessage(Core.color(plugin.getConfig().getString("gamemode-change").replace("{gamemode}", gameMode.name())));
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        } else if (args.length == 1){
            if (p.hasPermission("core.command.gamemode.others")){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setGameMode(gameMode);
                    sender.sendMessage(Core.color(plugin.getConfig().getString("gamemode-change-others").replace("{player}", target.getName()).replace("{gamemode}", gameMode.name())));
                    target.sendMessage(Core.color(plugin.getConfig().getString("gamemode-change").replace("{gamemode}", gameMode.name())));
                }
            } else {
                p.sendMessage(Core.color(plugin.getConfig().getString("no-permission")));
            }
        }
        return true;
    }
}