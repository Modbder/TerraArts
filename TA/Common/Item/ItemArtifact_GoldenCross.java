package TA.Common.Item;

import TA.Common.Entity.EntityFallingStar;
import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_GoldenCross extends ItemArtifact{

	public ItemArtifact_GoldenCross(String par1) {
		super(par1);
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		p.hurtResistantTime = 60;
		p.hurtTime = 60;
		return am;
	}
}
