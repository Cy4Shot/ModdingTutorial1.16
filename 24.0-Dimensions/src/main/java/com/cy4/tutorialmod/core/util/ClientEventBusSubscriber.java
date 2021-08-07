package com.cy4.tutorialmod.core.util;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.client.entity.ExampleEntityRenderer;
import com.cy4.tutorialmod.client.screen.DisplayCaseScreen;
import com.cy4.tutorialmod.client.ter.DisplayCaseTileEntityRenderer;
import com.cy4.tutorialmod.core.init.BlockInit;
import com.cy4.tutorialmod.core.init.ContainerTypesInit;
import com.cy4.tutorialmod.core.init.EntityTypesInit;
import com.cy4.tutorialmod.core.init.KeybindsInit;
import com.cy4.tutorialmod.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		
		KeybindsInit.register(event);
		
		ScreenManager.registerFactory(ContainerTypesInit.DISPLAY_CASE_CONTAINER_TYPE.get(), DisplayCaseScreen::new);
		
		RenderTypeLookup.setRenderLayer(BlockInit.DISPLAY_CASE.get(), RenderType.getCutout());
		
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.DISPLAY_CASE_TILE_ENTITY_TYPE.get(), DisplayCaseTileEntityRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.EXAMPLE.get(), ExampleEntityRenderer::new);
	}
}
