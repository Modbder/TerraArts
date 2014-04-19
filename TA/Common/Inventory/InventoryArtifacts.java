package TA.Common.Inventory;

import TA.Network.TAPacketHandler;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ReportedException;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InventoryArtifacts implements IInventory
{
    /**
     * An array of 36 item stacks indicating the main player inventory (including the visible bar).
     */
    public ItemStack[] mainInventory = new ItemStack[5];
    /** The player whose inventory this is. */
    public EntityPlayer player;
    public boolean inventoryChanged;
    public boolean isSlotClicked;

    public InventoryArtifacts(EntityPlayer par1EntityPlayer)
    {
        this.player = par1EntityPlayer;
    }

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return mainInventory[i];
	}

	@Override
    public ItemStack decrStackSize(int par1, int par2)
    {
	        if (this.mainInventory[par1] != null)
	        {
	            ItemStack itemstack;
	
	            if (this.mainInventory[par1].stackSize <= par2)
	            {
	                itemstack = this.mainInventory[par1];
	                this.mainInventory[par1] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.mainInventory[par1].splitStack(par2);
	
	                if (this.mainInventory[par1].stackSize == 0)
	                {
	                    this.mainInventory[par1] = null;
	                }
	
	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
        if (this.mainInventory[i] != null)
        {
            ItemStack itemstack = this.mainInventory[i];
            this.mainInventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
			mainInventory[i] = itemstack;
	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
	}

	@Override
	public String getInvName() {
		return "PlayerArtifact";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void onInventoryChanged() {
		if(!this.player.worldObj.isRemote)
		{
			DummyData[] datID = new DummyData[5];
			DummyData[] datMeta = new DummyData[5];
			DummyData[] datStr = new DummyData[5];
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = this.getStackInSlot(i);
				int id = 0;
				if(stack != null)
					id = stack.itemID;
				int meta = 0;
				if(stack != null)
					meta = stack.getItemDamage();
				datID[i] = new DummyData("stackID"+i,id);
				datMeta[i] = new DummyData("stackMeta"+i,meta);
				DataStorage.addDataToString(datID[i]);
				DataStorage.addDataToString(datMeta[i]);
			}
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = this.getStackInSlot(i);
				String dataStr = "||noData:0";
				if(stack != null)
				{
					NBTTagCompound tag = MiscUtils.getStackTag(stack);
					if(tag.hasKey("data"))
						dataStr = tag.getString("data");
				}
				int meta = 0;
				if(stack != null)
					meta = stack.getItemDamage();
				datStr[i] = new DummyData("data"+i,dataStr);
				DataStorage.addDataToString(datStr[i]);
			}
			String dataString = DataStorage.getDataString();
			PacketDispatcher.sendPacketToPlayer(TAPacketHandler.getPacketFor("TA.Sync", dataString), (Player) player);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
