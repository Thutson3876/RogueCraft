package me.thutson3876.roguecraft.cooldowns;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.abilities.Ability;

public class PlayerCooldownDragonBar {
	private final Player player;

	private final Map<Ability, BossBar> map = new HashMap<>();

	public PlayerCooldownDragonBar(Player player) {
		this.player = player;
	}
	
	public void deInit() {
		for(Map.Entry<Ability, BossBar> entry : map.entrySet()) {
			entry.getValue().setVisible(false);
			entry.getValue().removeAll();
		}
	}

	public void tick() {
		Player player = this.player;
		if (player == null || !player.isOnline()) {
			if (!this.map.isEmpty()) {
				for (BossBar bar : this.map.values()) {
					bar.removeAll();
					bar.setVisible(false);
				}
				this.map.clear();
			}
			return;
		}

		Map<Ability, Integer> cooldowns = Roguecraft.getPlugin().getCooldownManager().getAllCooldownsForPlayer(player);
		for (Map.Entry<Ability, Integer> entry : cooldowns.entrySet()) {
			Ability ability = entry.getKey();
			double remaining = entry.getValue();
			double max = ability.getCooldown();
			BossBar bar = this.map.get(ability);
			if (bar == null && remaining <= 1) {
				continue;
			}

			if (bar == null && remaining > 1) {
				bar = Bukkit.createBossBar(ability.getName(), BarColor.RED, BarStyle.SEGMENTED_20,
						new org.bukkit.boss.BarFlag[0]);
				bar.addPlayer(player);
				this.map.put(ability, bar);
				continue;
			}
			if (bar != null && remaining <= 1) {
				bar.setVisible(false);
				continue;
			}
			if (bar != null && remaining > 1) {
				double value = remaining / max;
				value = Math.min(Math.max(0.0D, value), 1.0D);
				bar.setProgress(value);
				if (!bar.isVisible()) {
					bar.setVisible(true);
				}
			}
		}
	}
}
