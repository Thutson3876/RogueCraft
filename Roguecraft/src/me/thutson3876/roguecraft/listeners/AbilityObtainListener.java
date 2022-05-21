package me.thutson3876.roguecraft.listeners;

import org.bukkit.event.Listener;

import me.thutson3876.roguecraft.Roguecraft;

public class AbilityObtainListener implements Listener {

	private static final Roguecraft plugin = Roguecraft.getPlugin();

	public AbilityObtainListener() {
		plugin.registerEvents(this);
	}
	
	
}
