package com.cy4.tutorialmod.core.init;

import com.cy4.tutorialmod.TutorialMod;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DimensionInit {
	public static final RegistryKey<World> EXAMPLE_DIMENSION_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
			new ResourceLocation(TutorialMod.MOD_ID, "example_dimension"));
}
