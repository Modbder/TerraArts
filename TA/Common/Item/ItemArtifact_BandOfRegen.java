package TA.Common.Item;

import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_BandOfRegen extends ItemArtifact{

	public ItemArtifact_BandOfRegen(String par1) {
		super(par1);
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		int ticks = MathUtils.makeLessetThan(p.ticksExisted, 120);
		if(ticks == 0 && p.getHealth() < p.getMaxHealth())
		{
			p.heal(1);
		}
	}

}
