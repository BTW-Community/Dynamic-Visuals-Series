package net.dravigen.dranimation_lib.utils;


import net.minecraft.src.Minecraft;

public class FrameDeltaHelper {
	private static float lastPartialTicks = 0;
	
	public static float getDeltaTicks(Minecraft mc) {
		float currentPartialTicks = mc.getTimer().renderPartialTicks;
		float delta;
		
		if (currentPartialTicks >= lastPartialTicks) {
			delta = currentPartialTicks - lastPartialTicks;
		}
		else {
			delta = (1.0f - lastPartialTicks) + currentPartialTicks;
		}
		
		lastPartialTicks = currentPartialTicks;
		
		return Math.min(delta, 1.0f);
	}
}