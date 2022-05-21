package me.thutson3876.roguecraft.playermanagement;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.abilities.Ability;
import me.thutson3876.roguecraft.abilities.OnHealAbility;
import me.thutson3876.roguecraft.abilities.OnHitAbility;
import me.thutson3876.roguecraft.abilities.OnInteractAbility;
import me.thutson3876.roguecraft.abilities.PassiveAbility;
import me.thutson3876.roguecraft.gui.AbilitiesGUI;

public class RoguePlayer {

	protected static final Roguecraft plugin = Roguecraft.getPlugin();
	
	Player bukkitPlayer;
	
	List<OnHitAbility> onHitAbilities = new ArrayList<>();
	List<OnHealAbility> onHealAbilities = new ArrayList<>();
	List<OnInteractAbility> onInteractAbilities = new ArrayList<>();
	List<PassiveAbility> passiveAbilities = new ArrayList<>();
	
	List<List<? extends Ability>> abilities = new ArrayList<>();
	
	AbilitiesGUI gui;
	
	boolean damageMeters = false;
	boolean friendlyFire = false;
	
	double dodgeChance = 0.00;
	
	double maxHealth = 20.0;
	
	float moveSpeed = 0.2f;
	
	public RoguePlayer(Player p) {
		bukkitPlayer = p;
		
		abilities.add(onHitAbilities);
		abilities.add(onHealAbilities);
		abilities.add(onInteractAbilities);
		abilities.add(passiveAbilities);
		
		gui = new AbilitiesGUI(bukkitPlayer, "Abilities", 27);
	}
	
	public boolean hasDamageMeters() {
		return damageMeters;
	}

	public void setDamageMeters(boolean damageMeters) {
		this.damageMeters = damageMeters;
	}
	
	public void addAbility(Ability abil) {
		if(abil instanceof OnHitAbility) {
			this.onHitAbilities.add((OnHitAbility) abil);
		}
		else if(abil instanceof OnHealAbility) {
			this.onHealAbilities.add((OnHealAbility) abil);
		}
		else if(abil instanceof OnInteractAbility) {
			this.onInteractAbilities.add((OnInteractAbility) abil);
		}
		else if(abil instanceof PassiveAbility) {
			this.passiveAbilities.add((PassiveAbility) abil);
		}
	}

	public List<Ability> getAbilities() {
		List<Ability> abilities = new ArrayList<>();
		
		for(List<? extends Ability> list : this.abilities)
			abilities.addAll(list);
			
		return abilities;
	}

	public void setFriendlyFire(boolean ff) {
		this.friendlyFire = ff;
	}

	public boolean hasFriendlyFire() {
		return this.friendlyFire;
	}
	
	public void openGUI() {
		bukkitPlayer.openInventory(gui.getInv());
	}

	public void deInit() {
	}

	public Player getPlayer() {
		return bukkitPlayer;
	}
}
