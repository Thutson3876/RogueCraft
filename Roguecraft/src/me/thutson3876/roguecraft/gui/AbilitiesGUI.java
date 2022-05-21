package me.thutson3876.roguecraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.abilities.Ability;
import me.thutson3876.roguecraft.playermanagement.RoguePlayer;

public class AbilitiesGUI implements Listener {

	private final Inventory inv;
	
	private RoguePlayer player;
	
	public AbilitiesGUI(Player p, final String title, int size) {
		Roguecraft plugin = Roguecraft.getPlugin();
		player = plugin.getPlayerManager().getPlayer(p);
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		inv = Bukkit.createInventory(null, size, title);
	}
	
	public void initializeItems() {
		for(Ability abil : player.getAbilities()) {
			abil.getItemStack();
		}
	}
	
	public void defaultFillGaps(Material filler) {
		ItemStack fill = new ItemStack(filler);
		ItemMeta meta = fill.getItemMeta();
		meta.setDisplayName(" ");
		fill.setItemMeta(meta);
		
		for (int i = 0; i < getInv().getSize(); i++) {
			ItemStack item = getInv().getItem(i);
			if (item == null || item.getType().equals(Material.AIR)) {
				getInv().setItem(i, fill);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (e.getInventory() != getInv())
			return;

		e.setCancelled(true);
	}

	@EventHandler
	public void onInventoryDrag(final InventoryDragEvent e) {
		if (e.getInventory().equals(getInv())) {
			e.setCancelled(true);
		}
	}

	public Inventory getInv() {
		return inv;
	}
}
