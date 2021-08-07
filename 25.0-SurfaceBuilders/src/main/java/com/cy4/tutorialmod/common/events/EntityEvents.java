package com.cy4.tutorialmod.common.events;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.core.init.EntityTypesInit;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class EntityEvents {
	@SubscribeEvent
	public static void onBiomeLoad(final BiomeLoadingEvent event) {
		if (event.getName() == null)
			return;
		MobSpawnInfoBuilder spawns = event.getSpawns();

		if (event.getCategory().equals(Biome.Category.THEEND)) {
			spawns.withSpawner(EntityClassification.MONSTER,
					new MobSpawnInfo.Spawners(EntityTypesInit.EXAMPLE.get(), 10, 3, 10));
		}
	}
}
