package me.thutson3876.roguecraft.commands.commandexecutors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.thutson3876.roguecraft.commands.AbstractCommand;
import me.thutson3876.roguecraft.playermanagement.RoguePlayer;
import me.thutson3876.roguecraft.util.ChatUtils;

public class Command_ToggleFriendlyFire extends AbstractCommand implements Listener {

	public Command_ToggleFriendlyFire() {
		super("togglefriendlyfire", "friendlyfire", "toggleff");
	}

	@Override
	protected boolean onInternalCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			RoguePlayer rplayer = plugin.getPlayerManager().getPlayer((Player)sender);
			
			if(rplayer != null) {
				boolean ff = !rplayer.hasFriendlyFire();
				
				if(ff) {
					sender.sendMessage(ChatUtils.chat("&aFriendly Fire has been toggled on!"));
				}
				else {
					sender.sendMessage(ChatUtils.chat("&4Friendly Fire has been toggled off!"));
				}
				rplayer.setFriendlyFire(ff);
				return true;
			}
		}
		
		sender.sendMessage("Error: Must be player to use this command");
		return true;
	}

}
