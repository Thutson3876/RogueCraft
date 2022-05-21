package me.thutson3876.roguecraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import me.thutson3876.roguecraft.Roguecraft;

public class BukkitEventListener implements Listener {

	private static final Roguecraft plugin = Roguecraft.getPlugin();

	public BukkitEventListener() {
		plugin.registerEvents(this);
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent e) {
		
	}
	
	
}
