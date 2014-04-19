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

public class ItemArtifact_StarCloak extends ItemArtifact{

	public ItemArtifact_StarCloak(String par1) {
		super(par1);
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		boolean shouldWork = true;
		if(TAUtils.playerInvTable.containsKey(p.username))
		{
			InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.username);
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = ia.mainInventory[i];
				if(stack != null && stack.getItem() instanceof IArtifact)
				{
					IArtifact art = (IArtifact) stack.getItem();
					if(art instanceof ItemArtifact_StarVeil)
					{
						shouldWork = false;
						break;
					}
				}
			}
		}
		if(shouldWork)
		for(int i = 0; i < 1+p.worldObj.rand.nextInt(5); ++i)
		{
			EntityFallingStar star = new EntityFallingStar(p.worldObj, p);
			star.setPositionAndRotation(p.posX+MathUtils.randomDouble(p.worldObj.rand)*4, p.posY+32,  p.posZ+MathUtils.randomDouble(p.worldObj.rand)*4, 0, 0);
			if(!p.worldObj.isRemote)
				p.worldObj.spawnEntityInWorld(star);
			star.motionX = 0;
			star.motionZ = 0;
		}
		return am;
	}
}
