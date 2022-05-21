package me.thutson3876.roguecraft.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import me.thutson3876.roguecraft.abilities.Ability;

public class ChatUtils {

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String traitListToString(List<Ability> list) {
		String abilityNames = "";
		for (Ability abil : list) {
			abilityNames += "&6" + abil.getCommandName() + "&3, ";
		}
		StringUtils.chop(abilityNames);
		StringUtils.chop(abilityNames);

		return chat("&3Abilities: " + abilityNames);
	}
	
	public static String toCommandName(String s) {
		return s.replaceAll(" ", "_");
	}
	
	public static String toRegularName(String s) {
		return s.replaceAll("_", " ");
	}
	
	public static List<String> splitStringAtLength(List<String> list, int splitLength, String colorCode){
		List<String> returnList = new ArrayList<>();
		for(String s : list) {
			returnList.addAll(splitSingleString(s, splitLength, colorCode));
		}
		
		return returnList;
	}
	
	private static List<String> splitSingleString(String s, int splitLength, String colorCode){
		List<String> returnList = new ArrayList<>();
		if(s == null)
			return null;
		if(s.length() > splitLength) {
			int i = s.indexOf(' ', splitLength - 15);
			if(i == -1)
				i = splitLength;
			
			String first = s.substring(0, i);
			String second = s.substring(i + 1);
			if(first.length() <= splitLength && second.length() <= splitLength) {
				returnList.add(colorCode + first);
				returnList.add(colorCode + second);

				return returnList;
			}
			if(first.length() > splitLength) {
				returnList.addAll(splitSingleString(first, splitLength, colorCode));
			}
			else {
				returnList.add(colorCode + first);
			}
			
			if(second.length() > splitLength) {
				returnList.addAll(splitSingleString(second, splitLength, colorCode));
			}
			else {
				returnList.add(colorCode + second);
			}
			
		}
		else {
			returnList.add(colorCode + s);
		}
		
		return returnList;
	}
	
	public static List<String> splitStringAtLength(List<String> list, int splitLength){
		List<String> returnList = new ArrayList<>();
		for(String s : list) {
			returnList.addAll(splitSingleString(s, splitLength));
		}
		
		return returnList;
	}
	
	private static List<String> splitSingleString(String s, int splitLength){
		List<String> returnList = new ArrayList<>();
		if(s == null)
			return null;
		if(s.length() > splitLength) {
			int i = s.indexOf(' ', splitLength - 15);
			if(i == -1)
				i = splitLength;
			
			String first = s.substring(0, i);
			String second = s.substring(i + 1);
			if(first.length() <= splitLength && second.length() <= splitLength) {
				returnList.add(first);
				returnList.add(second);

				return returnList;
			}
			if(first.length() > splitLength) {
				returnList.addAll(splitSingleString(first, splitLength));
			}
			else {
				returnList.add(first);
			}
			
			if(second.length() > splitLength) {
				returnList.addAll(splitSingleString(second, splitLength));
			}
			else {
				returnList.add(second);
			}
			
		}
		else {
			returnList.add(s);
		}
		
		return returnList;
	}
}
