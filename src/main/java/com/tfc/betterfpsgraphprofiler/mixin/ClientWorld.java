package com.tfc.betterfpsgraphprofiler.mixin;

import com.tfc.better_fps_graph.API.Profiler;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.world.ClientWorld.class)
public class ClientWorld {
	@Inject(at = @At("HEAD"), method = "tick(Ljava/util/function/BooleanSupplier;)V")
	private void tickBlocksPre(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick World (Blocks)");
	}
	
	@Inject(at = @At("RETURN"), method = "tick(Ljava/util/function/BooleanSupplier;)V")
	private void tickBlocksPost(CallbackInfo info) {
		Profiler.endSection();
	}
	
	@Inject(at = @At("HEAD"), method = "tickEntities()V")
	private void tickEntitiesPre(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick World (Entities)");
	}
	
	@Inject(at = @At("RETURN"), method = "tickEntities()V")
	private void tickEntitiesPost(CallbackInfo info) {
		Profiler.endSection();
	}
	
	@Inject(at = @At("HEAD"), method = "updateEntity(Lnet/minecraft/entity/Entity;)V")
	private void tickEntityPre(Entity entityIn, CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Entity " + entityIn.getType().getRegistryName());
	}
	
	@Inject(at = @At("RETURN"), method = "updateEntity(Lnet/minecraft/entity/Entity;)V")
	private void tickEntityPost(Entity entityIn, CallbackInfo info) {
		Profiler.addSection("minecraft:Tick World (Entities)");
	}
	
	@Inject(at = @At("HEAD"), method = "onChunkLoaded(II)V")
	private void tickEntityPre(int chunkX, int chunkZ, CallbackInfo info) {
		Profiler.addSection("minecraft:Load Chunk");
	}
	
	@Inject(at = @At("RETURN"), method = "onChunkLoaded(II)V")
	private void tickEntityPost(CallbackInfo info) {
		Profiler.endSection();
	}
}
