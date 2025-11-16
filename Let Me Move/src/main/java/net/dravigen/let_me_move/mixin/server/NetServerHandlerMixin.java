package net.dravigen.let_me_move.mixin.server;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetServerHandler.class)
public abstract class NetServerHandlerMixin extends NetHandler {
	@Redirect(method = "handleFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayerMP;isPlayerSleeping()Z", ordinal = 1))
	private boolean preventIllegalStance(EntityPlayerMP instance) {
		return true;
	}
}
