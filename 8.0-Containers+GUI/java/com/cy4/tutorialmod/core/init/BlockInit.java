package com.cy4.tutorialmod.core.init;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.block.DisplayCaseBlock;
import com.cy4.tutorialmod.common.block.QuarryBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			TutorialMod.MOD_ID);

	public static final RegistryObject<Block> EXAMLPE_BLOCK = BLOCKS.register("example_block",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

	public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry", () -> new QuarryBlock());
	public static final RegistryObject<Block> DISPLAY_CASE = BLOCKS.register("display_case", () -> new DisplayCaseBlock());
}
