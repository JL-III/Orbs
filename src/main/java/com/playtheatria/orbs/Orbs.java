package com.playtheatria.orbs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Orbs extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onProjectileLaunchEvent(PlayerInteractEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN +
                    "Event name: " + event.getEventName() + "\n" +
                    "Action: " + event.getAction() + "\n" +
                    "event.getItem: " + event.getItem() + "\n" +
                    "Items in hand: " + event.getPlayer().getInventory().getItemInMainHand().getType() + "\n" +
                    "Amount in hand: " + event.getPlayer().getInventory().getItemInMainHand().getAmount() + "\n"
            );
        }
    }

    @EventHandler
    public void onProjectileHitEvent(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Snowball snowball)) return;
        if (event.getEntity() instanceof Wolf wolf) {
            Entity entity = Bukkit.getWorld(event.getEntity().getWorld().getName()).spawnEntity(event.getEntity().getLocation(), event.getEntityType());
            if (!(entity instanceof Wolf wolf1)) return;
            event.setCancelled(true);
            wolf1.setAge(wolf.getAge());
            wolf1.setOwner(wolf.getOwner());
            wolf1.setCollarColor(wolf.getCollarColor());
            wolf.remove();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(
                        "Copied Wolf?"
                );
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(
                        "Event name: " + event.getEventName() + "\n" +
                                "Shooter: " + snowball.getShooter() + "\n" +
                                "Entity Name: " + event.getEntity().getName() + "\n" +
                                "EntityID: " + event.getEntity().getEntityId() + "\n" +
                                "UUID: " + event.getEntity().getUniqueId() + "\n" +
                                "Hit entity: " + event.getEntity() + "\n" +
                                "Entity Type: "  + event.getEntityType()
                );
            }
        }
    }
}
