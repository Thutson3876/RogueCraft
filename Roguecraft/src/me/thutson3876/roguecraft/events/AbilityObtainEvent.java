package me.thutson3876.roguecraft.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.thutson3876.roguecraft.abilities.Ability;
import me.thutson3876.roguecraft.abilities.Rarity;

public class AbilityObtainEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	
	private final Rarity rarity;
	
	private List<Ability> abilities = new ArrayList<>();
	
	public AbilityObtainEvent(Player player, Rarity rarity) {
		this.player = player;
		this.rarity = rarity;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
