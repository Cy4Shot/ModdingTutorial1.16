package com.cy4.tutorialmod.core.init;

import java.util.Random;

import com.cy4.tutorialmod.common.world.TreeSpawner;

import net.minecraft.world.gen.feature.Feature;

@SuppressWarnings("rawtypes")
public class TreeInit {
	public static final TreeSpawner EXAMPLE = new TreeSpawner() {
		@Override
		protected Feature getFeature(Random random) {
			return FeatureInit.EXAMPLE_TREE.get();
		}
	};
}
