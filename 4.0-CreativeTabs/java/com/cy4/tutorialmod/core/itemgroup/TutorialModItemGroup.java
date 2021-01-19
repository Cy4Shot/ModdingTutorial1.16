package com.cy4.tutorialmod.core.itemgroup;

import com.cy4.tutorialmod.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TutorialModItemGroup extends ItemGroup {

	public static final TutorialModItemGroup TUTORIAL_MOD = new TutorialModItemGroup(ItemGroup.GROUPS.length,
			"tutorial_mod");

	public TutorialModItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
	}

}
