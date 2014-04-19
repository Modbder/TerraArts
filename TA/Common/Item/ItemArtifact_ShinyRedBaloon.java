package TA.Common.Item;

import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_ShinyRedBaloon extends ItemArtifact{

	public ItemArtifact_ShinyRedBaloon(String par1) {
		super(par1);
	}
	
	@Override
	public void setJump(ItemStack par1ItemStack, EntityPlayer p) {
		p.motionY += 0.2F;
	}
	
	@Override
	public float setFallDistance(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		if(am > 2)
		{
			am -= 2;
		}
		return am;
	}

}
