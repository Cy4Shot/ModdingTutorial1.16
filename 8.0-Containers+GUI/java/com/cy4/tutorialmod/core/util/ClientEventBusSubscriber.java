package com.cy4.tutorialmod.core.util;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.client.screen.DisplayCaseScreen;
import com.cy4.tutorialmod.core.init.ContainerTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ContainerTypesInit.DISPLAY_CASE_CONTAINER_TYPE.get(), DisplayCaseScreen::new);
	}
}
