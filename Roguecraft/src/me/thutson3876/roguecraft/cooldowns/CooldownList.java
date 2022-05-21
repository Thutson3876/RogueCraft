package me.thutson3876.roguecraft.cooldowns;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.abilities.Ability;

public class CooldownList extends LinkedList<CooldownContainer> {

	private static final long serialVersionUID = -2404096703022530243L;

	public boolean contains(Player player, Ability ability) {
		return (get(player, ability) != null);
	}

	public CooldownContainer get(Player player, Ability ability) {
		for (CooldownContainer container : this) {
			if (container.getPlayer().equals(player)
					&& container.getAbility().getName().equalsIgnoreCase(ability.getName()))
				// ^could change back to comparing the abilitys themselves
				return container;
		}
		return null;
	}

	public CooldownContainer remove(Player player, Ability ability) {
		CooldownContainer container = get(player, ability);
		if (container != null)
			remove(container);
		return container;
	}

	public void add(Player player, Ability ability, int cooldownTime) {
		remove(player, ability);
		add(new CooldownContainer(player, ability, cooldownTime));
	}

	public void tickAll() {
		List<CooldownContainer> remove = new LinkedList<>();
		for (CooldownContainer container : this) {
			container.tick();
			if (container.getCooldownTime() <= 0)
				remove.add(container);
		}
		for (CooldownContainer removed : remove)
			remove(removed);
	}
}
