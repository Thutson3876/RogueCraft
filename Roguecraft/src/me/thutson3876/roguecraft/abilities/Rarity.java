package me.thutson3876.roguecraft.abilities;

import java.util.Arrays;
import java.util.Comparator;

public enum Rarity {

	COMMON(0), RARE(1), LEGENDARY(2);

	int level = 0;
	
	private Rarity(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public static Rarity[] sort(Rarity[] arr){
		Arrays.sort(arr, Comparator.comparing(Rarity::getLevel));
		return arr;
	}
}
