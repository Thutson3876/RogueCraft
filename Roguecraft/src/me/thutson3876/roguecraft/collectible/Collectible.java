package me.thutson3876.roguecraft.collectible;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;

public enum Collectible {

	ABILITY_PICKUP(Material.RESPAWN_ANCHOR);
	
	private Collectible(Material icon) {
		
	}
	
	public void saveCollectibles(Map<Collectible, List<Location>> map) {
		
	}
	
}
