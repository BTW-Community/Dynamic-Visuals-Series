package net.dravigen.let_me_move_ex.mixin.server;

import net.dravigen.dranimation_lib.packet.PacketUtils;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerConfigurationManager.class)
public abstract class ServerConfigurationManagerMixin {
	
	@Inject(method = "playerLoggedIn", at = @At("HEAD"))
	private void playerLogIn(EntityPlayerMP player, CallbackInfo ci) {
		PacketUtils.sendExtraIsPresent(player, true);
	}
}
