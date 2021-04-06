package com.cy4.tutorialmod.common.events;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.core.init.KeybindsInit;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
	
	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.world == null) return;
		onInput(mc, event.getKey(), event.getAction());
	}
	
	@SubscribeEvent
	public static void onMouseClick(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.world == null) return;
		onInput(mc, event.getButton(), event.getAction());
	}
	
	private static void onInput(Minecraft mc, int key, int action) {
		if (mc.currentScreen == null && KeybindsInit.exampleKey.isPressed()) {
			System.out.println("EXAMPLE KEY PRESSED");
		}
	}
}
