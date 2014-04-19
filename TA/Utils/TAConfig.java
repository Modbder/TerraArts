package TA.Utils;

import java.io.File;

import cpw.mods.fml.common.SidedProxy;
import TA.Network.Proxy.CommonProxy;
import DummyCore.Utils.IDummyConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.Configuration;

public class TAConfig implements IDummyConfig{

	@Override
	public void load(Configuration config) {
		cfg = config;
		cfg.load();
		mobID[0] = cfg.get("Entities", "fallingStarID", 414).getInt();
		cfg.save();
	}
	
	public int getIdForBlock(String name)
	{
		cfg.load();
		int cfgBlockID = cfg.getBlock(name, genericBlockIDS+blocksCount).getInt();
		++blocksCount;
		cfg.save();
		return cfgBlockID;
	}
	
	public int getIdForItem(String name)
	{
		cfg.load();
		int cfgItemID = cfg.getItem(name, genericItemIDS+itemsCount).getInt();
		++itemsCount;
		cfg.save();
		return cfgItemID;
	}
	
	public static Configuration cfg;
	public static int genericBlockIDS = 1770;
	public static int blocksCount = 0;
	public static int genericItemIDS = 17070;
	public static int itemsCount = 0;
	public static int[] mobID = new int[8];
}
