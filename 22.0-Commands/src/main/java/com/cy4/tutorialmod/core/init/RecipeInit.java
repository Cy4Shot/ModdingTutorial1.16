package com.cy4.tutorialmod.core.init;

import java.util.Map;

import com.cy4.tutorialmod.common.recipe.ExampleRecipe;
import com.cy4.tutorialmod.common.recipe.ExampleRecipeType;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class RecipeInit {
	public static final IRecipeType<ExampleRecipe> EXAMPLE_RECIPE = new ExampleRecipeType();

	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, EXAMPLE_RECIPE, ExampleRecipe.SERIALIZER);
	}

	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type,
			IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}

	public static Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> type, RecipeManager manager) {
		final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper
				.getPrivateValue(RecipeManager.class, manager, "field_199522_d");
		return recipes.get(type);
	}
}
