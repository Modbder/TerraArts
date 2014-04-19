package TA.Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import TA.API.IArtifact;
import TA.Common.Inventory.InventoryArtifacts;
import TA.Mod.TerraArts;
import TA.Utils.TAUtils;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class TAPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		try
		{
			ByteArrayInputStream b = new ByteArrayInputStream(packet.data, 0, packet.length);
			DataInputStream dis = new DataInputStream(b);
			if(packet.channel.equals("TA.Button"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer p = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					p.openGui(TerraArts.instance, 374435, p.worldObj, 0, 0, 0);
				}
			}
			if(packet.channel.equals("TA.PJump"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer p = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					TAUtils.onPlayerJump(p);
				}
			}
			if(packet.channel.equals("TA.JHold"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer p = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					TAUtils.onPlayerHoldJump(p);
				}
			}
			if(packet.channel.equals("TA.Particle"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String pname = dat[0].fieldValue;
					double pPosX = Double.parseDouble(dat[1].fieldValue);
					double pPosY = Double.parseDouble(dat[2].fieldValue);
					double pPosZ = Double.parseDouble(dat[3].fieldValue);
					double pMX = Double.parseDouble(dat[4].fieldValue);
					double pMY = Double.parseDouble(dat[5].fieldValue);
					double pMZ = Double.parseDouble(dat[6].fieldValue);
					EntityPlayer p = Minecraft.getMinecraft().thePlayer;
					p.worldObj.spawnParticle(pname, pPosX, pPosY, pPosZ, pMX, pMY, pMZ);
				}
			}
			if(packet.channel.equals("TA.Sound"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String pname = dat[0].fieldValue;
					double pPosX = Double.parseDouble(dat[1].fieldValue);
					double pPosY = Double.parseDouble(dat[2].fieldValue);
					double pPosZ = Double.parseDouble(dat[3].fieldValue);
					double v = Double.parseDouble(dat[4].fieldValue);
					double pitch = Double.parseDouble(dat[5].fieldValue);
					EntityPlayer p = Minecraft.getMinecraft().thePlayer;
					p.worldObj.playSound(pPosX, pPosY, pPosZ, pname, (float)v, (float)pitch, true);
				}
			}
			if(packet.channel.equals("TA.Move"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					double mX = Double.parseDouble(dat[0].fieldValue);
					double mY = Double.parseDouble(dat[1].fieldValue);
					double mZ = Double.parseDouble(dat[2].fieldValue);
					EntityPlayer p = Minecraft.getMinecraft().thePlayer;
					p.motionX += mX;
					p.motionY += mY;
					p.motionZ += mZ;
				}
			}
			if(packet.channel.equals("TA.Sync"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length > 10)
				{
					for(int i = 0; i < 5; ++i)
					{
						int id = Integer.parseInt(dat[i*2].fieldValue);
						int meta = Integer.parseInt(dat[(i*2)+1].fieldValue);
						String ddata = dat[(i)+10].fieldValue;
						if(id != 0)
						{
							InventoryArtifacts iarts = (InventoryArtifacts) TAUtils.clientInventory;
							iarts.mainInventory[i] = new ItemStack(id,1,meta);
							NBTTagCompound tag = MiscUtils.getStackTag(iarts.mainInventory[i]);
							tag.setString("data", ddata);
							iarts.mainInventory[i].setTagCompound(tag);
						}
					}
				}
			}
			if(packet.channel.equals("TA.Jump"))
			{
				String data = dis.readUTF();
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer p = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					if(TAUtils.playerInvTable.containsKey(p.username))
					{
						InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.username);
						for(int i = 0; i < 5; ++i)
						{
							ItemStack stack = ia.mainInventory[i];
							if(stack != null && stack.getItem() instanceof IArtifact)
							{
								IArtifact art = (IArtifact) stack.getItem();
								art.setJump(stack, p);
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public static void spawnParticleOnServer(String pName, double x, double y, double z, double par1, double par2, double par3, float range, int dimID)
	{
		DummyData[] datArray = new DummyData[7];
		datArray[0] = new DummyData("name",pName);
		datArray[1] = new DummyData("x",x);
		datArray[2] = new DummyData("y",y);
		datArray[3] = new DummyData("z",z);
		datArray[4] = new DummyData("px",par1);
		datArray[5] = new DummyData("py",par2);
		datArray[6] = new DummyData("pz",par3);
		for(int i = 0; i < 7; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		PacketDispatcher.sendPacketToAllAround(x, y, z, range, dimID, getPacketFor("TA.Particle",DataStorage.getDataString()));
	}
	
	public static void playSoundOnServer(String sName, double x, double y, double z, double par1, double par2, float range, int dimID)
	{
		DummyData[] datArray = new DummyData[6];
		datArray[0] = new DummyData("name",sName);
		datArray[1] = new DummyData("x",x);
		datArray[2] = new DummyData("y",y);
		datArray[3] = new DummyData("z",z);
		datArray[4] = new DummyData("v",par1);
		datArray[5] = new DummyData("p",par2);
		for(int i = 0; i < 6; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		PacketDispatcher.sendPacketToAllAround(x, y, z, range, dimID, getPacketFor("TA.Sound",DataStorage.getDataString()));
	}
	
	public static void changePositionOnServer(double x, double y, double z, EntityPlayer e)
	{
		DummyData[] datArray = new DummyData[3];
		datArray[0] = new DummyData("x",x);
		datArray[1] = new DummyData("y",y);
		datArray[2] = new DummyData("z",z);
		for(int i = 0; i < 3; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		PacketDispatcher.sendPacketToPlayer(getPacketFor("TA.Move",DataStorage.getDataString()),(Player) e);
	}
	
	public static Packet250CustomPayload getPacketFor(String packetName, String packetData)
	{
    	Packet250CustomPayload  m = new Packet250CustomPayload();
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(511);
    	DataOutputStream outputStream = new DataOutputStream(bos);
    	try 
    	{
    	    outputStream.writeUTF(packetData);
    	}catch (Exception ex)
    	{
            ex.printStackTrace();
            return null;
       	}
    	m.channel = packetName;
    	m.data = bos.toByteArray();
    	m.length = bos.size();
		return m;
	}

}
