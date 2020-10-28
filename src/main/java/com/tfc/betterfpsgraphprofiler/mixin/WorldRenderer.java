package com.tfc.betterfpsgraphprofiler.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tfc.better_fps_graph.API.Profiler;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.WorldRenderer.class)
public class WorldRenderer {
	@Inject(at = @At("HEAD"), method = "renderBlockLayer(Lnet/minecraft/client/renderer/RenderType;Lcom/mojang/blaze3d/matrix/MatrixStack;DDD)V")
	private void renderPre(RenderType p_228441_1_, MatrixStack p_228441_2_, double p_228441_3_, double p_228441_5_, double p_228441_7_, CallbackInfo info) {
		Profiler.addSection("minecraft:Render Block Layer " + p_228441_1_.toString());
	}
	
	@Inject(at = @At("RETURN"), method = "renderBlockLayer(Lnet/minecraft/client/renderer/RenderType;Lcom/mojang/blaze3d/matrix/MatrixStack;DDD)V")
	private void renderPost(RenderType p_228441_1_, MatrixStack p_228441_2_, double p_228441_3_, double p_228441_5_, double p_228441_7_, CallbackInfo info) {
		Profiler.endSection();
	}
}
