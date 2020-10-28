package com.tfc.betterfpsgraphprofiler.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tfc.better_fps_graph.API.Profiler;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.class)
public class TileEntityRenderer <E extends TileEntity> {
	@Inject(at = @At("HEAD"), method = "renderTileEntity(Lnet/minecraft/tileentity/TileEntity;FLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;)V")
	private void getFromCache(E tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, CallbackInfo info) {
		Profiler.addSection("minecraft:Get TE Renderer From Cache: " + tileEntityIn.getType().getRegistryName());
	}
	
	@Inject(at = @At("HEAD"), method = "renderTileEntity(Lnet/minecraft/tileentity/TileEntity;FLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;)V")
	private void renderPre(E tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, CallbackInfo info) {
		Profiler.addSection("minecraft:Render TE: " + tileEntityIn.getType().getRegistryName());
	}
	
	@Inject(at = @At("RETURN"), method = "renderTileEntity(Lnet/minecraft/tileentity/TileEntity;FLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;)V")
	private void renderPost(E tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, CallbackInfo info) {
		Profiler.endSection();
	}
}
