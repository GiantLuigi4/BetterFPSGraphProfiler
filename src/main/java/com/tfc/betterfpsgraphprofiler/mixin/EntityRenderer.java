package com.tfc.betterfpsgraphprofiler.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tfc.better_fps_graph.API.Profiler;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.entity.EntityRenderer.class)
public class EntityRenderer<T extends Entity> {
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/entity/Entity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V")
	private void renderPre(T p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_, CallbackInfo info) {
		Profiler.addSection("minecraft:Render Entity " + p_225623_1_.getType().getRegistryName());
	}
	
	@Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/entity/Entity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V")
	private void renderPost(T p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_, CallbackInfo info) {
		Profiler.endSection();
	}
}
