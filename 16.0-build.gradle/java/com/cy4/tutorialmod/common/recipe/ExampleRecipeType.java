package com.cy4.tutorialmod.common.recipe;

import com.cy4.tutorialmod.TutorialMod;

import net.minecraft.item.crafting.IRecipeType;

public class ExampleRecipeType implements IRecipeType<ExampleRecipe> {
	
	@Override
	public String toString() {
		return TutorialMod.MOD_ID + ":example_recipe";
	}

}
