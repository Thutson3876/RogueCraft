package me.thutson3876.roguecraft.cooldowns;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.abilities.Ability;

public class CooldownManager {

	protected CooldownList cooldownList;

	private int taskId;

	private final Map<Player, PlayerCooldownDragonBar> bars = new HashMap<>();

	public CooldownManager() {
		this.cooldownList = new CooldownList();

		init();
	}

	public void init() {
		Roguecraft plugin = Roguecraft.getPlugin();
		this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new UplinkReducingTask(), 1L, 1L);
	}
	
	public void deInit() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			PlayerCooldownDragonBar bar = bars.get(p);
			if(bar != null)
				bar.deInit();
		}
		
		clearAllCooldowns();
		shutdown();
	}

	public void shutdown() {
		Bukkit.getScheduler().cancelTask(this.taskId);
	}

	public int stillHasCooldown(Player player, Ability ability) {
		synchronized (this.cooldownList) {
			if (!this.cooldownList.contains(player, ability))
				return -1;
			return this.cooldownList.get(player, ability).getCooldownTime();
		}
	}

	public void setCooldown(Player player, Ability ability, int time) {
		synchronized (this.cooldownList) {
			if (time <= 0) {
				this.cooldownList.remove(player, ability);
			} else {
				this.cooldownList.add(player, ability, time);
			}
		}
	}

	public List<Ability> getAllCooldownsOfPlayer(Player p) {
		List<Ability> playerCooldownList = new LinkedList<>();
		synchronized (this.cooldownList) {
			for (CooldownContainer container : this.cooldownList) {
				if (container.getPlayer().equals(p))
					playerCooldownList.add(container.getAbility());
			}
		}
		return playerCooldownList;
	}
	
	public boolean resetPlayerCooldowns(Player p) {
		boolean hadCooldown = false;
		synchronized (this.cooldownList) {
			for (CooldownContainer container : this.cooldownList) {
				if (container.getPlayer().equals(p))
					cooldownList.remove(container);
				hadCooldown = true;
			}
		}
		return hadCooldown;
	}

	public Map<Ability, Integer> getAllCooldownsForPlayer(Player p) {
		Map<Ability, Integer> playerCooldownMap = new HashMap<>();
		synchronized (this.cooldownList) {
			for (CooldownContainer container : this.cooldownList) {
				if (container.getPlayer().equals(p))
					playerCooldownMap.put(container.getAbility(), Integer.valueOf(container.getCooldownTime()));
			}
		}
		return playerCooldownMap;
	}

	protected void tick() {
		if (this.cooldownList.isEmpty())
			return;
		synchronized (this.cooldownList) {
			this.cooldownList.tickAll();
		}
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerCooldownDragonBar bar = this.bars.get(player);
			if (bar == null) {
				this.bars.put(player, bar = new PlayerCooldownDragonBar(player));
			}

			bar.tick();
		}
	}

	protected class UplinkReducingTask implements Runnable {
		public void run() {
			CooldownManager.this.tick();
		}
	}

	public void clearAllCooldowns() {
		this.cooldownList.clear();
	}

}
