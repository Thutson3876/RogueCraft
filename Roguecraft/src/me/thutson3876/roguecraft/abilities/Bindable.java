package me.thutson3876.roguecraft.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface Bindable {

	Material getBoundType();

	void setBoundType(Material type);

	static boolean isHolding(Player p, Material type) {
		PlayerInventory inv = p.getInventory();
		ItemStack mainHand = inv.getItemInMainHand();
		ItemStack offHand = inv.getItemInOffHand();

		if (mainHand != null) {
			if (type.equals(mainHand.getType()))
				return true;
		}
		if (offHand != null) {
			if (type.equals(offHand.getType()))
				return true;
		}

		return false;
	}
}
