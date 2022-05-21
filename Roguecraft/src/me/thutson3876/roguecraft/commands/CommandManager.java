package me.thutson3876.roguecraft.commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import me.thutson3876.roguecraft.Roguecraft;
import me.thutson3876.roguecraft.commands.commandexecutors.Command_BindAbility;
import me.thutson3876.roguecraft.commands.commandexecutors.Command_Help;
import me.thutson3876.roguecraft.commands.commandexecutors.Command_SeeAbilities;
import me.thutson3876.roguecraft.commands.commandexecutors.Command_ToggleDamageMeters;
import me.thutson3876.roguecraft.commands.commandexecutors.Command_ToggleFriendlyFire;

public class CommandManager {
	private List<AbstractCommand> commands = new LinkedList<>();
	
	public CommandManager() {
		commands.add(new Command_SeeAbilities());
		commands.add(new Command_BindAbility());
		commands.add(new Command_Help());
		commands.add(new Command_ToggleDamageMeters());
		commands.add(new Command_ToggleFriendlyFire());
		
		this.registerCommands();
	}
	
	
	public void registerCommands() {
		Roguecraft plugin = Roguecraft.getPlugin();
		for(AbstractCommand command : this.commands) {
			plugin.getCommand(command.getCommandName()).setDescription(command.getDescription());
			plugin.getCommand(command.getCommandName()).setAliases(Arrays.asList(command.getAliases()));
			plugin.getCommand(command.getCommandName()).setTabCompleter(command);
			plugin.getCommand(command.getCommandName()).setExecutor(command);
		}
	}
}
