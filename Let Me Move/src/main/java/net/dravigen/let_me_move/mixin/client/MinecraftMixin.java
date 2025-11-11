package net.dravigen.let_me_move.mixin.client;

import net.dravigen.dranimation_lib.utils.AnimationUtils;
import net.dravigen.dranimation_lib.utils.GeneralUtils;
import net.dravigen.let_me_move.LetMeMoveAddon;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	
	@Shadow
	public WorldClient theWorld;
	@Shadow
	public EntityClientPlayerMP thePlayer;
	@Shadow
	private boolean isGamePaused;
	@Unique
	private long prevTime = 0;
	
	@Inject(method = "runGameLoop", at = @At("HEAD"))
	private void updateRender(CallbackInfo ci) {
		if (LetMeMoveAddon.isExtraLoaded()) return;
		
		long t = System.currentTimeMillis();
		float delta = (t - this.prevTime) / 25f;
		this.prevTime = t;
		
		delta = delta > 8 ? 8 : delta;
		
		AnimationUtils.delta = delta;
		
		EntityPlayer player = this.thePlayer;
		
		if (!this.isGamePaused && this.theWorld != null && player != null) {
			player.yOffset = GeneralUtils.incrementUntilGoal(player.yOffset, player.height - 0.18f, 0.4f * AnimationUtils.delta);
			
			float yaw = (player.renderYawOffset) % (360);
			
			yaw = yaw < 0 ? 360 + yaw : yaw;
			
			player.renderYawOffset = yaw;
		}
	}
}
