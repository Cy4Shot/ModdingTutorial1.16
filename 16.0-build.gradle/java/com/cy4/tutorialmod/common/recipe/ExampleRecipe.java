package com.cy4.tutorialmod.common.recipe;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.core.init.ItemInit;
import com.cy4.tutorialmod.core.init.RecipeInit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ExampleRecipe implements IRecipe<IInventory> {

	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input;
	private final ItemStack output;
	private final Block block;
	private final ResourceLocation id;

	public ExampleRecipe(ResourceLocation id, Ingredient input, ItemStack output, Block block) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.block = block;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.input.test(inv.getStackInSlot(0));
	}
	
	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return this.output.copy();
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}
	
	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
	
	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.EXAMPLE_RECIPE;
	}
	
	@Override
	public ItemStack getIcon() {
		return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
	}
	
	public boolean isValid(ItemStack input, Block block) {
		return this.input.test(input) && block == this.block;
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<ExampleRecipe> {

		Serializer() {
			this.setRegistryName(TutorialMod.MOD_ID, "example_recipe");
		}

		@Override
		public ExampleRecipe read(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isJsonArray(json, "input") ? JSONUtils.getJsonArray(json, "input")
					: JSONUtils.getJsonObject(json, "input");
			final Ingredient input = Ingredient.deserialize(inputEl);

			final ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));

			final Block block = ForgeRegistries.BLOCKS
					.getValue(new ResourceLocation(JSONUtils.getString(json, "block")));

			if (block == null) {
				throw new IllegalStateException("Block does not exist.");
			}

			return new ExampleRecipe(recipeId, input, output, block);
		}

		@Override
		public ExampleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.read(buffer);
			final ItemStack output = buffer.readItemStack();
			final Block block = ForgeRegistries.BLOCKS.getValue(buffer.readResourceLocation());
			
			if (block == null) {
				throw new IllegalStateException("Block does not exist.");
			}

			return new ExampleRecipe(recipeId, input, output, block);
		}

		@Override
		public void write(PacketBuffer buffer, ExampleRecipe recipe) {
			recipe.input.write(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeResourceLocation(recipe.block.getRegistryName());
		}
	}
}
