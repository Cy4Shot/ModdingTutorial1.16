package com.cy4.tutorialmod.core.init;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.common.material.CustomArmorMaterial;
import com.cy4.tutorialmod.common.material.CustomToolMaterial;
import com.cy4.tutorialmod.core.itemgroup.TutorialModItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			TutorialMod.MOD_ID);

	public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
			() -> new Item(new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_SWORD = ITEMS.register("example_sword",
			() -> new SwordItem(CustomToolMaterial.EXAMPLE_TOOL, 5, -1f,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_AXE = ITEMS.register("example_axe",
			() -> new SwordItem(CustomToolMaterial.EXAMPLE_TOOL, 5, -1f,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_PICKAXE = ITEMS.register("example_pickaxe",
			() -> new SwordItem(CustomToolMaterial.EXAMPLE_TOOL, 5, -1f,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_SHOVEL = ITEMS.register("example_shovel",
			() -> new SwordItem(CustomToolMaterial.EXAMPLE_TOOL, 5, -1f,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_HOE = ITEMS.register("example_hoe",
			() -> new SwordItem(CustomToolMaterial.EXAMPLE_TOOL, 5, -1f,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_HELMET = ITEMS.register("example_helmet",
			() -> new ArmorItem(CustomArmorMaterial.EXAMPLE_ARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_CHESTPLATE = ITEMS.register("example_chestplate",
			() -> new ArmorItem(CustomArmorMaterial.EXAMPLE_ARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_LEGGINGS = ITEMS.register("example_leggings",
			() -> new ArmorItem(CustomArmorMaterial.EXAMPLE_ARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));

	public static final RegistryObject<Item> EXAMPLE_BOOTS = ITEMS.register("example_boots",
			() -> new ArmorItem(CustomArmorMaterial.EXAMPLE_ARMOR, EquipmentSlotType.FEET,
					new Item.Properties().group(TutorialModItemGroup.TUTORIAL_MOD)));
}
