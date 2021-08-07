package com.cy4.tutorialmod.core.init;

import java.awt.event.KeyEvent;

import com.cy4.tutorialmod.TutorialMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class KeybindsInit {
	public static KeyBinding exampleKey;
	
	public static void register(final FMLClientSetupEvent event) {
		exampleKey = create("example_key", KeyEvent.VK_G);
		
		ClientRegistry.registerKeyBinding(exampleKey);
	}
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + TutorialMod.MOD_ID + "." + name, key, "key.category." + TutorialMod.MOD_ID);
	}
}
