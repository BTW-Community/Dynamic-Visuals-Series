package net.dravigen.dranimation_lib.mixin;

import net.dravigen.dranimation_lib.packet.PacketUtils;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetServerHandler.class)
public class NetServerHandlerMixin {
	@Shadow
	public EntityPlayerMP playerEntity;
	
	@Inject(method = "handleCustomPayload", at = @At("HEAD"))
	private void tu_onCustomPayloadC2S(Packet250CustomPayload packet, CallbackInfo ci) {
		switch (packet.channel) {
			case PacketUtils.ANIMATION_SYNC_CHANNEL -> PacketUtils.handleAnimationSync(packet, this.playerEntity);
			case PacketUtils.HUNGER_EXHAUSTION_CHANNEL ->
					PacketUtils.handleExhaustionFromClient(packet, this.playerEntity);
			case PacketUtils.ANIMATION_DATA_SYNC_CHANNEL ->
					PacketUtils.handleAnimationDataOnServer(packet, this.playerEntity);
		}
	}
}
