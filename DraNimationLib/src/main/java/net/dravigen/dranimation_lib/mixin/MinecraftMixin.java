package net.dravigen.dranimation_lib.mixin;

import net.dravigen.dranimation_lib.settings.DVS_ConfigManager;
import net.dravigen.dranimation_lib.utils.AnimationUtils;
import net.dravigen.dranimation_lib.utils.FrameDeltaHelper;
import net.minecraft.src.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	
	@Inject(method = "runGameLoop", at = @At("HEAD"))
	private void updateDelta(CallbackInfo ci) {
		AnimationUtils.delta = FrameDeltaHelper.getDeltaTicks((Minecraft) (Object) this);
	}
	
	@Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
	private void saveSettings(CallbackInfo ci) {
		DVS_ConfigManager.save();
	}
}
