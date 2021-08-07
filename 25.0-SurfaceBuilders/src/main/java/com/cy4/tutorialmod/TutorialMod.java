package com.cy4.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cy4.tutorialmod.common.entity.ExampleEntity;
import com.cy4.tutorialmod.common.item.CustomSpawnEggItem;
import com.cy4.tutorialmod.core.config.TutorialConfig;
import com.cy4.tutorialmod.core.init.BiomeInit;
import com.cy4.tutorialmod.core.init.BlockInit;
import com.cy4.tutorialmod.core.init.CommandInit;
import com.cy4.tutorialmod.core.init.ContainerTypesInit;
import com.cy4.tutorialmod.core.init.EntityTypesInit;
import com.cy4.tutorialmod.core.init.FeatureInit;
import com.cy4.tutorialmod.core.init.ItemInit;
import com.cy4.tutorialmod.core.init.RecipeInit;
import com.cy4.tutorialmod.core.init.SurfaceBuilderInit;
import com.cy4.tutorialmod.core.init.TileEntityTypesInit;
import com.cy4.tutorialmod.core.itemgroup.TutorialModItemGroup;
import com.cy4.tutorialmod.core.network.TutorialNetwork;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("tutorialmod")
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class TutorialMod {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "tutorialmod";

	public TutorialMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::commonSetup);
		bus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

		EntityTypesInit.ENTITY_TYPES.register(bus);
		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
		TileEntityTypesInit.TILE_ENTITY_TYPE.register(bus);
		ContainerTypesInit.CONTAINER_TYPES.register(bus);
		SurfaceBuilderInit.SURFACE_BUILDERS.register(bus);
		BiomeInit.BIOMES.register(bus);
		BiomeInit.registerBiomes();

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
	
		ModLoadingContext.get().registerConfig(Type.COMMON, TutorialConfig.SPEC, "tutorialmod-common.toml");
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry()
					.register(new BlockItem(block, new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD))
							.setRegistryName(block.getRegistryName()));
		});
	}
	
	@SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		CustomSpawnEggItem.initSpawnEggs();
	}
	
	@SubscribeEvent
	public void onCommandRegister(final RegisterCommandsEvent event) {
		CommandInit.registerCommands(event);
	}

	@SuppressWarnings("deprecation")
	public void commonSetup(final FMLCommonSetupEvent event) {
		TutorialNetwork.init();
		
		DeferredWorkQueue.runLater(() -> {
			GlobalEntityTypeAttributes.put(EntityTypesInit.EXAMPLE.get(), ExampleEntity.setAttributes().create());
		});
	}
}
