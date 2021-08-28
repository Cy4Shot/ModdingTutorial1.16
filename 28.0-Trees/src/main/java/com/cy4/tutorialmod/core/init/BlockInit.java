package com.cy4.tutorialmod.core.init;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.block.CustomSaplingBlock;
import com.cy4.tutorialmod.common.block.DisplayCaseBlock;
import com.cy4.tutorialmod.common.block.QuarryBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			TutorialMod.MOD_ID);

	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

	public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry", () -> new QuarryBlock());

	public static final RegistryObject<Block> DISPLAY_CASE = BLOCKS.register("display_case",
			() -> new DisplayCaseBlock());

	public static final RegistryObject<Block> EXAMPLE_LOG = BLOCKS.register("example_log",
			() -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD)
					.hardnessAndResistance(2f, 10f).harvestLevel(0).harvestTool(ToolType.AXE)));

	public static final RegistryObject<Block> EXAMPLE_STRIPPED_LOG = BLOCKS.register("example_stripped_log",
			() -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD)
					.hardnessAndResistance(2f, 3f).harvestLevel(0).harvestTool(ToolType.AXE)));

	public static final RegistryObject<Block> EXAMPLE_LEAVES = BLOCKS.register("example_leaves",
			() -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)
					.hardnessAndResistance(0.2f, 1f).tickRandomly().notSolid()));
	
	public static final RegistryObject<Block> EXAMPLE_SAPLING = BLOCKS.register("example_sapling",
			() -> new CustomSaplingBlock(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)
					.hardnessAndResistance(0f).tickRandomly().notSolid(), TreeInit.EXAMPLE));
}
