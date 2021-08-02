package com.cy4.tutorialmod.core.init;

import java.util.ArrayList;

import com.cy4.tutorialmod.common.command.BaseCommand;
import com.cy4.tutorialmod.common.command.impl.ExampleCommand;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;

public class CommandInit {
	private static final ArrayList<BaseCommand> commands = new ArrayList<>();

	public static void registerCommands(final RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		commands.add(new ExampleCommand("give_example_item", 4, true));
		
		commands.forEach(command -> {
			if (command.isEnabled() && command.setExecution() != null) {
				dispatcher.register(command.getBuilder());
			}
		});
	}
}
