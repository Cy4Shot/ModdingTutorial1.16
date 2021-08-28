package com.cy4.tutorialmod.common.world;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

@SuppressWarnings("rawtypes")
public abstract class TreeSpawner {
	@Nullable
	protected abstract Feature getFeature(Random random);
	
	@SuppressWarnings("unchecked")
	public boolean spawn(ISeedReader reader, ChunkGenerator generator, BlockPos pos, BlockState blockUnder, Random random) {
		Feature tree = getFeature(random);
		if (tree == null) return false;
		
		reader.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		if (tree.generate(reader, generator, random, pos, new NoFeatureConfig())) {
			return true;
		}
		
		reader.setBlockState(pos, blockUnder, 4);
		return false;
	}
}
