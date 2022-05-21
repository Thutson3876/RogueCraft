package me.thutson3876.roguecraft.commands.commandexecutors;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.thutson3876.roguecraft.abilities.Ability;
import me.thutson3876.roguecraft.abilities.Bindable;
import me.thutson3876.roguecraft.commands.AbstractCommand;
import me.thutson3876.roguecraft.playermanagement.RoguePlayer;
import me.thutson3876.roguecraft.util.ChatUtils;

public class Command_BindAbility extends AbstractCommand implements Listener {

	public Command_BindAbility() {
		super("bindability", new String[] { "bind" });
	}

	@Override
	protected boolean onInternalCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&4Error: Must be a player to use this command"));
			return true;
		}
		if (args.length != 1) {
			return false;
		}

		Player player = (Player) sender;
		List<String> values = boundAbilityNames(player);
		if(values.isEmpty()) {
			sender.sendMessage(ChatUtils.chat("&4Error: You have no abilities that require binding"));
			return true;
		}
		
		for(String s : values) {
			if(s.equalsIgnoreCase(args[0])) {
				RoguePlayer rplayer = plugin.getPlayerManager().getPlayer(player);
				for(Ability abil : rplayer.getAbilities()) {
					if(s.equalsIgnoreCase(abil.getCommandName())) {
						Material type = player.getInventory().getItemInMainHand().getType();
						if(type == null || type.equals(Material.AIR)) {
							sender.sendMessage(ChatUtils.chat("&6" + s + " &bhas been unbound"));
							((Bindable)abil).setBoundType(null);
							return true;
						}
							
						((Bindable)abil).setBoundType(type);
						rplayer.addAbility(abil);
						sender.sendMessage(ChatUtils.chat("&bSuccessfully bound &6" + s + " &bto &6" + type));
						return true;
					}
				}
			}
		}

		sender.sendMessage(ChatUtils.chat("&4Error: Not a bindable ability"));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> values = new LinkedList<>();
		if (!(sender instanceof Player))
			return values;
		Player player = (Player) sender;

		if (args.length == 1)
			values = boundAbilityNames(player);

		return values;
	}

	private static List<String> boundAbilityNames(Player p) {
		List<String> values = new LinkedList<>();

		RoguePlayer rplayer = plugin.getPlayerManager().getPlayer(p);

		for (Ability abil : rplayer.getAbilities()) {
			if (abil instanceof Bindable)
				values.add(abil.getCommandName());
		}

		return values;
	}
}
