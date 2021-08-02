package com.cy4.tutorialmod.common.te;

import com.cy4.tutorialmod.core.init.TileEntityTypesInit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

	public QuarryTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public QuarryTileEntity() {
		this(TileEntityTypesInit.QUARRY_TILE_ENTITY_TYPE.get());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		if (!this.world.getBlockState(pos.down()).isAir()
				&& this.world.getBlockState(pos.down()).getBlockHardness(world, pos) >= 0) {
			this.world.addEntity(new ItemEntity(world, pos.getX() + .5d, pos.getY() + 1.5d, pos.getZ() + .5d,
					new ItemStack(this.world.getBlockState(pos.down()).getBlock())));
			this.world.setBlockState(this.pos.down(), Blocks.AIR.getDefaultState());
		}
	}
}
