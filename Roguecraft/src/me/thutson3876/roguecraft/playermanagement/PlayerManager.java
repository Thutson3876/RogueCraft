package me.thutson3876.roguecraft.playermanagement;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {

	private Set<RoguePlayer> players = new HashSet<>();

	public Set<RoguePlayer> getRoguePlayers() {
		return players;
	}

	public RoguePlayer getPlayer(Player player) {
		for (RoguePlayer p : players) {
			if (p.getPlayer().equals(player)) {
				return p;
			}
		}

		return null;
	}

	public void init() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			addPlayer(p);
		}
	}

	public void deInit() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			removePlayer(p);
		}
	}

	public boolean addPlayer(Player player) {
		if (player == null || players.contains(getPlayer(player)))
			return false;

		RoguePlayer rPlayer = new RoguePlayer(player);

		players.add(rPlayer);

		return true;
	}

	public void removePlayer(Player player) {
		RoguePlayer rPlayer = getPlayer(player);
		if (rPlayer == null) {
			return;
		}

		rPlayer.deInit();

		players.remove(rPlayer);
	}

	public boolean contains(Player player) {
		for (RoguePlayer p : players) {
			if (p.getPlayer().equals(player)) {
				return true;
			}
		}

		return false;
	}

}
