package me.thutson3876.roguecraft.commands.commandexecutors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import me.thutson3876.roguecraft.commands.AbstractCommand;
import me.thutson3876.roguecraft.util.ChatUtils;

public class Command_Help extends AbstractCommand implements Listener {

	public Command_Help() {
		super("fantasyhelp");
	}

	@Override
	protected boolean onInternalCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatUtils.chat("&2&lRoguecraft Help:"));
		sender.sendMessage(ChatUtils.chat("&6&nGetting Started: "));
		sender.sendMessage(ChatUtils.chat("&3Use command &f/seeabilities &3to see the abilities you've obtained."));

		return true;
	}

}
