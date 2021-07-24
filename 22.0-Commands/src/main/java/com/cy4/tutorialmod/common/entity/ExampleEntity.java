package com.cy4.tutorialmod.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ExampleEntity extends MobEntity {

	public ExampleEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute setAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0f)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0f)
				.createMutableAttribute(Attributes.ATTACK_SPEED, 1.4f)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.4f);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		
		this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	protected int getExperiencePoints(PlayerEntity player) {
		return 5;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOGLIN_DEATH;
	}

}
