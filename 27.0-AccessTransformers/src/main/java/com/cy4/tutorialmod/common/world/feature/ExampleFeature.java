package com.cy4.tutorialmod.common.world.feature;

import java.util.Random;

import com.cy4.tutorialmod.core.init.BlockInit;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ExampleFeature extends Feature<NoFeatureConfig> {
	
	private static final BlockState EXAMPLE_BLOCK = BlockInit.EXAMPLE_BLOCK.get().getDefaultState();
	private static final BlockState STONE = Blocks.SHROOMLIGHT.getDefaultState();

	public ExampleFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@SuppressWarnings("deprecation")
	public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		if (!(reader instanceof IWorldReader)) {
			return reader.hasBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
		} else {
			return reader.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos));
		}
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos,
			NoFeatureConfig config) {

		while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
			pos = pos.down();
		}
		pos = pos.up();
		
		for (int i = 0; i < 4; i++) 
			setBlockState(reader, pos.up(i), EXAMPLE_BLOCK);
		setBlockState(reader, pos.down(1), STONE);

		return false;
	}

}
