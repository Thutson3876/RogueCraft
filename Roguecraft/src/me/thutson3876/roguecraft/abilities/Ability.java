package me.thutson3876.roguecraft.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public interface Ability {

	boolean trigger(Event event);

	long getCooldown();

	Player getPlayer();

	ItemStack getItemStack();

	String getName();

	String getInstructions();

	String getDescription();

	String getCommandName();
	
	Priority getPriority();
	
	Rarity getRarity();
	
	void levelUp();

	void applyLevelModifiers();

	void triggerCooldown();
	
	void deInit();
	
	static List<Ability> generateRandomAbilities(Rarity rarity, int amt){
		List<Ability> abilList = new ArrayList<>();
		
		//generate random abilities based on rarity and add them
		
		return abilList;
	}
}
