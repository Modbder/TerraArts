package TA.Network.Proxy;

import TA.Client.GUI.GuiArtifacts;
import TA.Common.Inventory.ContainerArtifacts;
import TA.Common.Inventory.ContainerChest;
import TA.Common.Tile.TileEntityTAChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 374435)
		{
			return new ContainerArtifacts(player.inventory, player.worldObj.isRemote, player);
		}
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile instanceof TileEntityTAChest)
		{
			return new ContainerChest(player.inventory, (TileEntityTAChest) tile);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void registerRenderInformation()
	{
		
	}

}
