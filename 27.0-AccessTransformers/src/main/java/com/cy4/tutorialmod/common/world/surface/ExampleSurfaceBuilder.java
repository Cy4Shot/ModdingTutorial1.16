package com.cy4.tutorialmod.common.world.surface;

import java.util.Random;

import com.cy4.tutorialmod.core.init.BlockInit;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ExampleSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

	public static final BlockState EXAMPLE_BLOCK = BlockInit.EXAMPLE_BLOCK.get().getDefaultState();
	public static final BlockState STONE = Blocks.STONE.getDefaultState();

	public static final SurfaceBuilderConfig EXAMPLE_BLOCK_CONFIG = new SurfaceBuilderConfig(EXAMPLE_BLOCK,
			EXAMPLE_BLOCK, EXAMPLE_BLOCK);
	public static final SurfaceBuilderConfig STONE_CONFIG = new SurfaceBuilderConfig(STONE, STONE, STONE);

	public ExampleSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
		super(codec);
	}

	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		if (random.nextInt(2) == 0) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, EXAMPLE_BLOCK_CONFIG);
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, STONE_CONFIG);
		}
	}
}
