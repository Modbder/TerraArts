package TA.Network.Proxy;

import TA.Client.GUI.GuiArtifacts;
import TA.Common.Entity.EntityFallingStar;
import TA.Common.Inventory.ContainerChest;
import TA.Common.Tile.TileEntityTAChest;
import TA.Utils.TAClientTickHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 374435)
		{
			return new GuiArtifacts(player.inventory);
		}
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile instanceof TileEntityTAChest)
		{
			return new GuiChest(player.inventory, (TileEntityTAChest) tile);
		}
		return null;
	}
	
	@Override
	public void registerRenderInformation()
	{
		TickRegistry.registerTickHandler(new TAClientTickHandler(), Side.CLIENT);
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingStar.class, new RenderSnowball(Item.diamond));
	}

}
