package com.cy4.tutorialmod.core.util;

import com.cy4.tutorialmod.core.init.BlockInit;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

public class StrippingMap {
	public static void createStrippable(Block input, Block output) {
		AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
		AxeItem.BLOCK_STRIPPING_MAP.put(input, output);
	}
	
	public static void registerStrippables() {
		createStrippable(BlockInit.EXAMPLE_LOG.get(), BlockInit.EXAMPLE_STRIPPED_LOG.get());
	}
}
