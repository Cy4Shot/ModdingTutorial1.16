package com.cy4.tutorialmod.common.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.core.init.RecipeInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class TutorialJEIPlugin implements IModPlugin {
	
	private static final ResourceLocation PLUGIN_ID = new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager manager = Minecraft.getInstance().world.getRecipeManager();
		
		registration.addRecipes(getRecipes(manager, RecipeInit.EXAMPLE_RECIPE), ExampleRecipeCategory.ID);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new ExampleRecipeCategory(helper));
	}
	
	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
}
