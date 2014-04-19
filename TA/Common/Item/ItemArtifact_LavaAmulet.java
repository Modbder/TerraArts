package TA.Common.Item;

import TA.API.IArtifact;
import TA.Common.Inventory.InventoryArtifacts;
import TA.Network.TAPacketHandler;
import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_LavaAmulet extends ItemArtifact{

	public ItemArtifact_LavaAmulet(String par1) {
		super(par1);
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("data"))
			{
				tag.setString("data", "||sprint:140");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = Integer.parseInt(dat[0].fieldValue);
				Material material = p.worldObj.getBlockMaterial((int)p.posX, (int)p.posY-1, (int)p.posZ);
				boolean isInLava = false;
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking())
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				material = p.worldObj.getBlockMaterial((int)p.posX, (int)p.posY+1, (int)p.posZ);
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking() && !isInLava)
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				material = p.worldObj.getBlockMaterial((int)p.posX, (int)p.posY, (int)p.posZ);
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking() && !isInLava)
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				if(jumped < -1)
				{
					jumped = 0;
				}
				int maxLavaResistance = 140;
				if(TAUtils.playerInvTable.containsKey(p.username))
				{
					InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.username);
					for(int i = 0; i < 5; ++i)
					{
						ItemStack stack = ia.mainInventory[i];
						if(stack != null && stack.getItem() instanceof IArtifact)
						{
							IArtifact art = (IArtifact) stack.getItem();
							if(art instanceof ItemArtifact_LavaWalkers)
							{
								maxLavaResistance += 140;
								break;
							}
						}
					}
				}
				if(!isInLava && jumped < maxLavaResistance)
				{
					++jumped;
				}
				DummyData jDat = new DummyData("sprint",jumped);
				tag.setString("data", jDat.toString());
				par1ItemStack.setTagCompound(tag);
			}
		}
		
	}

}
