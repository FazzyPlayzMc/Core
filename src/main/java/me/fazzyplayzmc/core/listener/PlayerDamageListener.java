package me.fazzyplayzmc.core.listener;

import me.fazzyplayzmc.core.Core;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;


public class PlayerDamageListener implements Listener {

    private Core plugin;

    public PlayerDamageListener(Core plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        Entity entity = e.getEntity();
        if (entity instanceof Player p){
            if (plugin.getGodPlayers().contains(p)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){
        Entity entity = e.getEntity();
        if (entity instanceof Player p){
            if (plugin.getGodPlayers().contains(p)){
                e.setCancelled(true);
            }
        }
    }

}
