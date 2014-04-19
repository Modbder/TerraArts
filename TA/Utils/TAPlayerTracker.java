package TA.Utils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import TA.Common.Inventory.ContainerArtifacts;
import TA.Common.Inventory.InventoryArtifacts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.IPlayerTracker;

public class TAPlayerTracker implements IPlayerTracker{
	
	public File createFilesFor(String username, String name)
	{
		try 
		{
			int length = 0;
			for(int i = 0; i < name.length(); ++i)
			{
				if(name.substring(0, i).contains("\\.\\"))
				{
					--length;
					--length;
					break;
				}
				++length;
			}
			String print = name.substring(0,length);
			print += "saves\\";
			print += MinecraftServer.getServer().getFolderName();
			print += "\\TA\\";
			File f = new File(print);
			f.mkdirs();
			File file = new File(print+"TArtsData_"+username+".dat");
			if(!file.exists())
				file.createNewFile();
			return file;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public InventoryArtifacts createInventoryFor(EntityPlayer player, String username)
	{
		InventoryArtifacts iarts = new InventoryArtifacts(player);
		TAUtils.playerInvTable.put(username, iarts);
		TAUtils.playerInvContainer.put(username, new ContainerArtifacts(player.inventory, player.worldObj.isRemote, player));
		return iarts;
	}
	
	public void loadInventoryFromFile(File file,InventoryArtifacts iarts)
	{
		try {
			FileInputStream stream = new FileInputStream(file);
			DataInputStream data = new DataInputStream(stream);
			for(int i = 0; i < 5; ++i)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag = (NBTTagCompound) NBTTagCompound.readNamedTag(data);
				if(tag.hasKey("id"))
					iarts.mainInventory[i] = new ItemStack(0,0,0);
				if(iarts.mainInventory[i] != null)
					iarts.mainInventory[i].readFromNBT(tag);
			}
			data.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) {
			String username = player.username;
			String name = "";
			if(!player.worldObj.isRemote)
			{
				name = MinecraftServer.getServer().getFile(MinecraftServer.getServer().getFolderName()).getAbsolutePath();
			}
			InventoryArtifacts ia = createInventoryFor(player,username);
			if(!player.worldObj.isRemote)
				loadInventoryFromFile(createFilesFor(username, name), ia);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		try {
			if(!player.worldObj.isRemote)
			{
				String username = player.username;
				String name = MinecraftServer.getServer().getFile(MinecraftServer.getServer().getFolderName()).getAbsolutePath();
				int length = 0;
				for(int i = 0; i < name.length(); ++i)
				{
					if(name.substring(0, i).contains("\\.\\"))
					{
						--length;
						--length;
						break;
					}
					++length;
				}
				String print = name.substring(0,length);
				print += "saves\\";
				print += MinecraftServer.getServer().getFolderName();
				print += "\\TA\\";
				File f = new File(print);
				f.mkdirs();
				File file = new File(print+"TArtsData_"+username+".dat");
				try {
					if(!file.exists())
						file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				try {
					FileOutputStream stream = new FileOutputStream(file);
					DataOutputStream data = new DataOutputStream(stream);
					InventoryArtifacts iarts = (InventoryArtifacts) TAUtils.playerInvTable.get(username);
					for(int i = 0; i < 5; ++i)
					{
						ItemStack st = iarts.mainInventory[i];
						NBTTagCompound tag = new NBTTagCompound();
						if(st != null)
						{
							tag = st.writeToNBT(tag);
						}
						tag.writeNamedTag(tag, data);
					}
					data.flush();
					data.close();
					stream.flush();
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
