package com.tfc.betterfpsgraphprofiler.mixin;

import com.tfc.better_fps_graph.API.Profiler;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//TODO: EmitterParticle mixin
@Mixin(net.minecraft.client.particle.ParticleManager.class)
public class ParticleManager {
	@Inject(at = @At("HEAD"), method = "tick()V")
	private void tickPre(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Particles");
	}
	
	@Inject(at = @At("HEAD"), method = "tickParticleList(Ljava/util/Collection;)V")
	private void tickListPre(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Particle List");
	}
	
	@Inject(at = @At("HEAD"), method = "tickParticle(Lnet/minecraft/client/particle/Particle;)V")
	private void tickParticlePre(Particle particle, CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Particle " + particle.getClass());
	}
	
	@Inject(at = @At("HEAD"), method = "renderParticles(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer$Impl;Lnet/minecraft/client/renderer/LightTexture;Lnet/minecraft/client/renderer/ActiveRenderInfo;F)V")
	private void renderParticlesPre(CallbackInfo info) {
		Profiler.addSection("minecraft:Render Particles");
	}
	
	@Inject(at = @At("RETURN"), method = "tick()V")
	private void tickPost(CallbackInfo info) {
		Profiler.endSection();
	}
	
	@Inject(at = @At("RETURN"), method = "tickParticleList(Ljava/util/Collection;)V")
	private void tickListPost(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Particles");
	}
	
	@Inject(at = @At("RETURN"), method = "tickParticle(Lnet/minecraft/client/particle/Particle;)V")
	private void tickParticlePost(CallbackInfo info) {
		Profiler.addSection("minecraft:Tick Particle List");
	}
	
	@Inject(at = @At("RETURN"), method = "renderParticles(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer$Impl;Lnet/minecraft/client/renderer/LightTexture;Lnet/minecraft/client/renderer/ActiveRenderInfo;F)V")
	private void renderParticlePost(CallbackInfo info) {
		Profiler.endSection();
	}
}
