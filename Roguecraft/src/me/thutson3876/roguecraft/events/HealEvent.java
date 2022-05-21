package me.thutson3876.roguecraft.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HealEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean cancelled = false;
	
	private final LivingEntity target;
	
	private final Entity source;
	
	private double healAmt;
	
	private final double baseHealAmt;
	
	public HealEvent(LivingEntity target, Entity source, double heal) {
		this.target = target;
		this.source = source;
		this.healAmt = heal;
		this.baseHealAmt = heal;
	}
	
	
	public double getHealAmt() {
		return healAmt;
	}

	public double getBaseHealAmt() {
		return baseHealAmt;
	}

	public void setHealAmt(double healAmt) {
		this.healAmt = healAmt;
	}

	public LivingEntity getTarget() {
		return target;
	}


	public Entity getSource() {
		return source;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}


	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
