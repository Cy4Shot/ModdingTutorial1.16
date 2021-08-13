package com.cy4.tutorialmod.common.material;

import java.util.function.Supplier;

import com.cy4.tutorialmod.core.init.ItemInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum CustomArmorMaterial implements IArmorMaterial {
	EXAMPLE_ARMOR("example", 10, new int[] { 4, 7, 9, 4 }, 17, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 4f, 0.2f,
			() -> Ingredient.fromItems(ItemInit.EXAMPLE_ITEM.get()));

	private static final int[] baseDurability = { 128, 144, 160, 112 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] armorVal;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	CustomArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability,
			SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.armorVal = armorVal;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient.get();

	}

	@Override
	public int getDurability(EquipmentSlotType slot) {
		return baseDurability[slot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) {
		return this.armorVal[slot.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairIngredient;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
