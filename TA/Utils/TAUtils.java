package TA.Utils;

import java.util.Hashtable;
import java.util.UUID;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import TA.API.IArtifact;
import TA.Common.Inventory.InventoryArtifacts;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeHooks;

public class TAUtils {
	public static Hashtable<String,IInventory> playerInvTable = new Hashtable<String,IInventory>();
	public static Hashtable<String,Container> playerInvContainer = new Hashtable<String,Container>();
	
	public static IInventory clientInventory;
	
	public static void applySpeedModifier(EntityPlayer p, String uuidLast5Symbols, double modifier, boolean remove)
	{
		if(p.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)) == null)
		{
			if(!remove)
				p.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").applyModifier(new AttributeModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols),"movementSpeedTerraArts", modifier, 2));
		}else if(remove)
		{
			if(p.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)) != null)
				p.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").removeModifier(p.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)));
		}
	}
	
	public static void applyKnokbackModifier(EntityPlayer p, String uuidLast5Symbols, double modifier, boolean remove)
	{
		if(p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)) == null)
		{
			if(!remove)
				p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols),"generic.knockbackResistance", modifier, 0));
		}else if(remove)
		{
			if(p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)) != null)
				p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).removeModifier(p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).getModifier(UUID.fromString("CB3F55A3-645C-4F38-A497-9C13A33"+uuidLast5Symbols)));
		}
	}
	
	public static void onPlayerJump(EntityPlayer p)
	{
		if(TAUtils.playerInvTable.containsKey(p.username))
		{
			InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.username);
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = ia.mainInventory[i];
				if(stack != null && stack.getItem() instanceof IArtifact)
				{
					IArtifact art = (IArtifact) stack.getItem();
					NBTTagCompound tag = MiscUtils.getStackTag(stack);
					if(!tag.hasKey("data"))
					{
						tag.setString("data", "||jump:0");
					}
					String dataString = tag.getString("data");
					DummyData[] dat = DataStorage.parseData(dataString);
					if(dat.length > 0)
					{
						int jumped = Integer.parseInt(dat[0].fieldValue);
					}
					if(art.performJump(stack, p))
					{
				        p.motionY = 0.41999998688697815D;

				        if (p.isPotionActive(Potion.jump))
				        {
				            p.motionY += (double)((float)(p.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
				        }

				        if (p.isSprinting())
				        {
				            float f = p.rotationYaw * 0.017453292F;
				            p.motionX -= (double)(MathHelper.sin(f) * 0.2F);
				            p.motionZ += (double)(MathHelper.cos(f) * 0.2F);
				        }

				        p.isAirBorne = true;
				        ForgeHooks.onLivingJump(p);
						break;
					}
				}
			}
		}
	}
	
	public static void onPlayerHoldJump(EntityPlayer p)
	{
		if(TAUtils.playerInvTable.containsKey(p.username))
		{
			InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.username);
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = ia.mainInventory[i];
				if(stack != null && stack.getItem() instanceof IArtifact)
				{
					IArtifact art = (IArtifact) stack.getItem();
					NBTTagCompound tag = MiscUtils.getStackTag(stack);
					if(art.holdJump(stack, p))
					{
						break;
					}
				}
			}
		}
	}
}
