package com.cy4.tutorialmod.common.events;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.core.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.FORGE)
public class PlayerEvents {

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		PlayerEntity player = event.player;
		player.inventory.armorInventory.set(0, new ItemStack(Items.GOLDEN_HELMET));
	}
	
	@SubscribeEvent
	public static void onPlayerToss(ItemTossEvent event) {
		PlayerEntity player = event.getPlayer();
		World world = player.getEntityWorld();
		BlockState state = world.getBlockState(player.getPosition().down());
		
		if (state.getBlock() == BlockInit.EXAMLPE_BLOCK.get()) {
			player.addItemStackToInventory(new ItemStack(Items.DIAMOND));
		}
	}
}
