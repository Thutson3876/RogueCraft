package me.thutson3876.roguecraft.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean cancelled = false;
	
	private final Entity target;
	
	private final Entity source;
	
	private double damage;
	
	private final double baseDamage;
	
	private final DamageCause cause;
	
	public DamageEvent(Entity target, Entity source, double damage, DamageCause cause) {
		this.target = target;
		this.source = source;
		this.damage = damage;
		this.baseDamage = damage;
		this.cause = cause;
	}
	
	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public double getBaseDamage() {
		return baseDamage;
	}

	public Entity getTarget() {
		return target;
	}

	public Entity getSource() {
		return source;
	}

	public DamageCause getCause() {
		return cause;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
