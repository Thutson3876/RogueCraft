package me.thutson3876.roguecraft.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.thutson3876.roguecraft.util.ChatUtils;
import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.playermanagement.RoguePlayer;

public abstract class AbstractAbility implements Ability {
	
	protected static final Roguecraft plugin = Roguecraft.getPlugin();

	protected RoguePlayer rplayer;
	protected Player player;

	protected String displayName;
	
	protected final Priority priority;
	protected final Rarity rarity;
	
	protected int currentLevel = 0;
	protected int coolDowninTicks = 0;
	
	protected ItemStack itemStack;
	
	public AbstractAbility(Player p, String name, int cooldownInTicks, Material icon) {
		if (p != null) {
			player = p;
			rplayer = plugin.getPlayerManager().getPlayer(p);
		}
		this.coolDowninTicks = 16;
		this.displayName = name;
		this.priority = Priority.NORMAL;
		this.rarity = Rarity.COMMON;
		
		this.createItemStack(icon);
	}

	protected ItemStack createItemStack(Material mat) {
		ItemStack item = new ItemStack(mat);
		List<String> lore = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		lore.add("Level: &6" + this.currentLevel);
		temp.add(this.getDescription());
		lore.addAll(ChatUtils.splitStringAtLength(temp, 35, "&r"));
		temp = new ArrayList<>();
		temp.add(this.getInstructions());
		lore.addAll(ChatUtils.splitStringAtLength(temp, 35, "&3"));
		
		if(this instanceof Bindable) {
			Bindable bindable = (Bindable)this;
			if(bindable.getBoundType() == null) {
				lore.add("&bBindable");
			}
			else {
				lore.add("&bBound to: &6" + bindable.getBoundType().name());
			}
		}
		
		List<String> loreFinal = new ArrayList<>();
		lore = ChatUtils.splitStringAtLength(lore, 35);
		
		for(String s : lore) {
			loreFinal.add(ChatUtils.chat(s));
		}
		

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&6" + this.displayName));
		meta.setLore(loreFinal);
		item.setItemMeta(meta);
		this.itemStack = item;
		return this.itemStack;
	}

	@Override
	public boolean trigger(Event event) {
		return false;
	}

	@Override
	public void triggerCooldown() {
		plugin.getCooldownManager().setCooldown(player, this, this.coolDowninTicks);
	}

	@Override
	public long getCooldown() {
		return this.coolDowninTicks;
	}

	public boolean isOnCooldown() {
		return (-1 != plugin.getCooldownManager().stillHasCooldown(player, this));
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public ItemStack getItemStack() {
		updateItemStack();
		return itemStack;
	}

	private void updateItemStack() {
		this.itemStack = createItemStack(this.itemStack.getType());
	}

	@Override
	public String getName() {
		return this.displayName;
	}

	@Override
	public String getCommandName() {
		return this.getName().replaceAll(" ", "_");
	}
	
	@Override
	public Priority getPriority() {
		return this.priority;
	}

	@Override
	public void levelUp() {
		this.currentLevel++;
		applyLevelModifiers();
		this.updateItemStack();
	}

	@Override
	public void deInit() {
	}

}
