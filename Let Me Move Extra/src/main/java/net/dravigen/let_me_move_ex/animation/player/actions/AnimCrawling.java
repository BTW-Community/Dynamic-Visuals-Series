package net.dravigen.let_me_move_ex.animation.player.actions;

import net.dravigen.dranimation_lib.utils.AnimationUtils;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ResourceLocation;

import static net.dravigen.dranimation_lib.utils.GeneralUtils.isEntityHeadNormalHeightInsideBlock;
import static net.dravigen.dranimation_lib.utils.GeneralUtils.isInsideWater;
import static net.dravigen.let_me_move_ex.LetMeMoveExAddon.crawl_key;

public class AnimCrawling extends AnimSwimming {
	public static final ResourceLocation id = new ResourceLocation("LMM", "crawling");
	
	public AnimCrawling() {
		super(id);
	}
	
	@Override
	public boolean shouldActivateAnimation(EntityPlayer player, AxisAlignedBB axisAlignedBB) {
		if (!AnimationUtils.extraIsPresent) return false;
		
		return super.shouldActivateAnimation(player, axisAlignedBB);
	}
	
	@Override
	public boolean isGeneralConditonsMet(EntityPlayer player, AxisAlignedBB axisAlignedBB) {
		return !player.capabilities.isFlying && player.onGround && !isInsideWater(player) && !player.isOnLadder();
	}
	
	@Override
	public boolean isActivationConditonsMet(EntityPlayer player, AxisAlignedBB axisAlignedBB) {
		return crawl_key.pressed || isEntityHeadNormalHeightInsideBlock(player);
	}
}
