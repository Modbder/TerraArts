package TA.Common.Item;

import TA.Common.Entity.EntityFallingStar;
import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArtifact_LuckyEmerald extends ItemArtifact{

	public ItemArtifact_LuckyEmerald(String par1) {
		super(par1);
	}
	
	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		if(p.worldObj.rand.nextFloat()<0.05F)
		{
			ItemStack emerald = new ItemStack(Item.emerald,1,0);
			p.dropPlayerItem(emerald);
		}
		return am;
	}
}
