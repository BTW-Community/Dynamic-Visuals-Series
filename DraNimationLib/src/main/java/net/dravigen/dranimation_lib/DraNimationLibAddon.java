package net.dravigen.dranimation_lib;

import btw.AddonHandler;
import btw.BTWAddon;
import net.dravigen.dranimation_lib.settings.DVS_ConfigManager;

public class DraNimationLibAddon extends BTWAddon {
	public DraNimationLibAddon() {
		super();
	}
	
	@Override
	public void initialize() {
		AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		DVS_ConfigManager.SMOOTH_ANIMATION = DVS_ConfigManager.registerBool("smoothAnim",
																			"Smooth Animations",
																			true,
																			"Smooths animation's transitions and movements",
																			"");
	}
}