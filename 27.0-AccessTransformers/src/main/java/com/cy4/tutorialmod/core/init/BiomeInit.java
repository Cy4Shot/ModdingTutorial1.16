package com.cy4.tutorialmod.core.init;

import java.util.function.Supplier;

import com.cy4.tutorialmod.TutorialMod;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,
			TutorialMod.MOD_ID);

	// Add more biomes here!
	static {
		createBiome("example_biome", BiomeMaker::makeVoidBiome);
	}

	// (and also here!)
	public static RegistryKey<Biome> EXAMPLE_BIOME = registerBiome("example_biome");

	public static RegistryKey<Biome> registerBiome(String biomeName) {
		return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(TutorialMod.MOD_ID, biomeName));
	}

	public static RegistryObject<Biome> createBiome(String biomeName, Supplier<Biome> biome) {
		return BIOMES.register(biomeName, biome);
	}

	public static void registerBiomes() {
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(EXAMPLE_BIOME, 10));
	}
}
