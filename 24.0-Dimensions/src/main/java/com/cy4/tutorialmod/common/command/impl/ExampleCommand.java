package com.cy4.tutorialmod.common.command.impl;

import com.cy4.tutorialmod.common.command.BaseCommand;
import com.cy4.tutorialmod.core.init.ItemInit;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class ExampleCommand extends BaseCommand {

	public ExampleCommand(String name, int permissionLevel, boolean enabled) {
		super(name, permissionLevel, enabled);
	}

	@Override
	public LiteralArgumentBuilder<CommandSource> setExecution() {
		return builder.then(Commands.argument("player", EntityArgument.player())
				.executes(source -> execute(source.getSource(), EntityArgument.getPlayer(source, "player"))));
	}

	private int execute(CommandSource source, ServerPlayerEntity player) {
		player.addItemStackToInventory(new ItemStack(ItemInit.EXAMPLE_ITEM.get()));
		return Command.SINGLE_SUCCESS;
	}

}
