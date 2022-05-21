package me.thutson3876.roguecraft.abilities;

import java.util.Arrays;
import java.util.Comparator;

public enum Priority {

	HIGHEST(2), HIGH(1), NORMAL(0), LOW(1), LOWEST(-2);
	
	int level = 0;
	
	private Priority(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public static Priority[] sort(Priority[] arr){
		Arrays.sort(arr, Comparator.comparing(Priority::getLevel));
		return arr;
	}
}
