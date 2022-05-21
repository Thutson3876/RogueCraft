package me.thutson3876.roguecraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.thutson3876.roguecraft.collectible.Collectible;
import me.thutson3876.roguecraft.commands.CommandManager;
import me.thutson3876.roguecraft.cooldowns.CooldownManager;
import me.thutson3876.roguecraft.custommobs.CustomMobManager;
import me.thutson3876.roguecraft.playermanagement.PlayerManager;

public class Roguecraft extends JavaPlugin {

	protected static Roguecraft plugin;
	
	private CommandManager commandManager;

	private CooldownManager cooldownManager;

	private PlayerManager playerManager;

	private CustomMobManager mobManager;

	private Map<Collectible, List<Location>> collectiblesToLoad = new HashMap<>();
	
	private Map<Collectible, List<Location>> collectiblesToSave = new HashMap<>();

	@Override
	public void onEnable() {
		for(Collectible c : Collectible.values()) {
			collectiblesToLoad.put(c, new ArrayList<>());
			collectiblesToSave.put(c, new ArrayList<>());
		}
		
		plugin = this;
		playerManager = new PlayerManager();
		playerManager.init();
		cooldownManager = new CooldownManager();
		commandManager = new CommandManager();
		mobManager = new CustomMobManager();
		mobManager.init();

		registerListeners();
		log("Roguecraft has been loaded!");
	}

	@Override
	public void onDisable() {
		playerManager.deInit();
		mobManager.deInit();
		cooldownManager.deInit();
		for(Collectible c : Collectible.values()) {
			c.saveCollectibles(collectiblesToSave);
		}
		
		this.saveConfig();
		log("Roguecraft has been disabled!");
	}
	
	private void registerListeners() {
		
	}
	
	public static Roguecraft getPlugin() {
		return plugin;
	}
	
	public void registerEvents(Listener listener) {
		if (listener == null)
			return;
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public CooldownManager getCooldownManager() {
		return this.cooldownManager;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void log(String message) {
		System.out.println(message);
	}
	
	public void log(String string, Throwable exp) {
		System.out.println(string);
		System.out.println(exp);
	}
}
