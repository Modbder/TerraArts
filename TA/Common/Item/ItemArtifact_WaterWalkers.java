package TA.Common.Item;

import TA.Network.TAPacketHandler;
import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_WaterWalkers extends ItemArtifact{

	public ItemArtifact_WaterWalkers(String par1) {
		super(par1);
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		Material material = p.worldObj.getBlockMaterial((int)p.posX, (int)p.posY-1, (int)p.posZ);
		if(material == Material.water && p.motionY < 0 && !p.isInWater() && !p.isSneaking())
		{
			TAPacketHandler.changePositionOnServer(0, -p.motionY, 0, p);
			p.motionY = 0;
		}
	}

}
