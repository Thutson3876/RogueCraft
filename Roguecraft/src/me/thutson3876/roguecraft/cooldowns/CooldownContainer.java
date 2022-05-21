package me.thutson3876.roguecraft.cooldowns;

import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.abilities.Ability;

public class CooldownContainer {
	private final Player player;

	private final Ability ability;

	private int cooldownTime;

	public CooldownContainer(Player player, Ability ability, int cooldownTime) {
		this.player = player;
		this.ability = ability;
		this.cooldownTime = cooldownTime;
	}

	public void tick() {
		this.cooldownTime--;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Ability getAbility() {
		return this.ability;
	}

	public int getCooldownTime() {
		return this.cooldownTime;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CooldownContainer other = (CooldownContainer) obj;
		if (this.ability.getName() == null) {
			if (other.ability.getName() != null)
				return false;
		} else if (!this.ability.equals(other.ability)) {
			return false;
		}
		if (this.cooldownTime != other.cooldownTime)
			return false;
		if (this.player == null) {
			if (other.player != null)
				return false;
		} else if (!this.player.equals(other.player)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return this.player.getDisplayName() + " has: " + this.cooldownTime + " on: " + this.ability.getName();
	}
}
