package com.cy4.tutorialmod.client.entity;

import com.cy4.tutorialmod.TutorialMod;
import com.cy4.tutorialmod.client.entity.model.ExampleEntityModel;
import com.cy4.tutorialmod.common.entity.ExampleEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, ExampleEntityModel<ExampleEntity>> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/example/example.png");
	
	public ExampleEntityRenderer(EntityRendererManager manager) {
		super(manager, new ExampleEntityModel<>(), 0.7f);
	}

	@Override
	public ResourceLocation getEntityTexture(ExampleEntity entity) {
		return TEXTURE;
	}
}
