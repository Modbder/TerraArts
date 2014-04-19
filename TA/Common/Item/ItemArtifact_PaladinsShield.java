package TA.Common.Item;

import TA.API.IArtifact;
import TA.Common.Entity.EntityFallingStar;
import TA.Common.Inventory.InventoryArtifacts;
import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_PaladinsShield extends ItemArtifact{

	public ItemArtifact_PaladinsShield(String par1) {
		super(par1);
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		return am *= 0.75F;
	}
}
