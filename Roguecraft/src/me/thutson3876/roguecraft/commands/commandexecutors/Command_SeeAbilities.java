package me.thutson3876.roguecraft.commands.commandexecutors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thutson3876.roguecraft.commands.AbstractCommand;
import me.thutson3876.roguecraft.util.ChatUtils;

public class Command_SeeAbilities extends AbstractCommand {

	public Command_SeeAbilities() {
		super("seeabilities", "abilities");
	}

	@Override
	protected boolean onInternalCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			plugin.getPlayerManager().getPlayer((Player)sender).openGUI();
			return true;
		}
		
		sender.sendMessage(ChatUtils.chat("&4Error: Must be a player to use this command"));
		return true;
	}

}
