package com.cy4.tutorialmod.core.init;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.te.DisplayCaseTileEntity;
import com.cy4.tutorialmod.common.te.QuarryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, TutorialMod.MOD_ID);

	public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("quarry",
					() -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.QUARRY.get()).build(null));
	
	public static final RegistryObject<TileEntityType<DisplayCaseTileEntity>> DISPLAY_CASE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("display_case",
					() -> TileEntityType.Builder.create(DisplayCaseTileEntity::new, BlockInit.DISPLAY_CASE.get()).build(null));

}
