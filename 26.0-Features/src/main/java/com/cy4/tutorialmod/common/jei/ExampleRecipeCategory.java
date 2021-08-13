package com.cy4.tutorialmod.common.jei;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.recipe.ExampleRecipe;
import com.cy4.tutorialmod.core.init.ItemInit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ExampleRecipeCategory implements IRecipeCategory<ExampleRecipe> {
	
	public static final ResourceLocation ID = new ResourceLocation(TutorialMod.MOD_ID, ".example_recipe_category");
	
	private final IDrawable back;
	private final IDrawable icon;
	
	public ExampleRecipeCategory(IGuiHelper helper) {
		this.back = helper.createBlankDrawable(180, 100);
		this.icon = helper.createDrawableIngredient(new ItemStack(ItemInit.EXAMPLE_ITEM.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends ExampleRecipe> getRecipeClass() {
		return ExampleRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + TutorialMod.MOD_ID + ".example_recipe").getString();
	}

	@Override
	public IDrawable getBackground() {
		return back;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(ExampleRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ExampleRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, true, 0, 36);
		itemStackGroup.init(2, false, 60, 18);
		
		itemStackGroup.set(ingredients);
	}

}
