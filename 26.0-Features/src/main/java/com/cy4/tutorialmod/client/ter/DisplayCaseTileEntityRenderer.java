package com.cy4.tutorialmod.client.ter;

import com.cy4.tutorialmod.common.te.DisplayCaseTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class DisplayCaseTileEntityRenderer extends TileEntityRenderer<DisplayCaseTileEntity> {

	private Minecraft mc = Minecraft.getInstance();

	public DisplayCaseTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(DisplayCaseTileEntity te, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (te.getItem().equals(ItemStack.EMPTY) || te.getItem().getItem().equals(Items.AIR))
			return;

		ClientPlayerEntity player = mc.player;
		int lightLevel = getLightLevel(te.getWorld(), te.getPos().up());

		renderItem(te.getItem(), new double[] { 0.5d, 1d, 0.5d },
				Vector3f.YP.rotationDegrees(180f - player.rotationYaw), matrixStackIn, bufferIn, partialTicks,
				combinedOverlayIn, lightLevel, 0.8f);

		ITextComponent label = te.getItem().hasDisplayName() ? te.getItem().getDisplayName()
				: new TranslationTextComponent(te.getItem().getTranslationKey());

		renderLabel(matrixStackIn, bufferIn, lightLevel, new double[] { .5d, 1.3d, .5d }, label, 0xffffff);
	}

	private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack,
			IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int lightLevel, float scale) {
		matrixStack.push();
		matrixStack.translate(translation[0], translation[1], translation[2]);
		matrixStack.rotate(rotation);
		matrixStack.scale(scale, scale, scale);

		IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);
		mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer,
				lightLevel, combinedOverlay, model);
		matrixStack.pop();
	}

	private void renderLabel(MatrixStack stack, IRenderTypeBuffer buffer, int lightLevel, double[] corner,
			ITextComponent text, int color) {

		FontRenderer font = mc.fontRenderer;

		stack.push();
		float scale = 0.01f;
		int opacity = (int) (.4f * 255.0f) << 24;
		float offset = (float) (-font.getStringPropertyWidth(text) / 2);
		Matrix4f matrix = stack.getLast().getMatrix();

		stack.translate(corner[0], corner[1] + .4f, corner[2]);
		stack.scale(scale, scale, scale);
		stack.rotate(mc.getRenderManager().getCameraOrientation());
		stack.rotate(Vector3f.ZP.rotationDegrees(180f));

		font.func_243247_a(text, offset, 0, color, false, matrix, buffer, false, opacity, lightLevel);
		stack.pop();
	}

	private int getLightLevel(World world, BlockPos pos) {
		int bLight = world.getLightFor(LightType.BLOCK, pos);
		int sLight = world.getLightFor(LightType.SKY, pos);
		return LightTexture.packLight(bLight, sLight);
	}

}
