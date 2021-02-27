package com.cy4.tutorialmod.common.te;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.container.DisplayCaseContainer;
import com.cy4.tutorialmod.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DisplayCaseTileEntity extends LockableLootTileEntity {
	
	public static int slots = 1;
	
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	protected DisplayCaseTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}
	
	public DisplayCaseTileEntity() {
		this(TileEntityTypesInit.DISPLAY_CASE_TILE_ENTITY_TYPE.get());
	}

	@Override
	public int getSizeInventory() {
		return slots;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + TutorialMod.MOD_ID + ".display_case");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new DisplayCaseContainer(id, player, this);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if(!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		
		return compound;
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}
	}
}
