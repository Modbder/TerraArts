package TA.Utils;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import TA.Common.Inventory.InventoryArtifacts;
import TA.Network.TAPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TAClientTickHandler implements ITickHandler{

	public static boolean isKeyPressed = false;
	public static boolean isJumpPressed = false;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		World w = mc.theWorld;
		EntityPlayer player = mc.thePlayer;
		if(w != null && player != null && (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative))
		{
			if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode))
			{
				DummyData a0 = new DummyData("username",player.username);
				DataStorage.addDataToString(a0);
				String dataString = DataStorage.getDataString();
				PacketDispatcher.sendPacketToServer(TAPacketHandler.getPacketFor("TA.JHold", dataString));
				TAUtils.onPlayerHoldJump(player);
			}
			if(!isJumpPressed && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode) && player.worldObj.isAirBlock((int)player.posX, (int)(player.posY-1.620D)-1, (int)player.posZ) && !player.onGround && player.worldObj.isRemote)
			{
				isJumpPressed = true;
				DummyData a0 = new DummyData("username",player.username);
				DataStorage.addDataToString(a0);
				String dataString = DataStorage.getDataString();
				PacketDispatcher.sendPacketToServer(TAPacketHandler.getPacketFor("TA.PJump", dataString));
				TAUtils.onPlayerJump(player);
			}
			if(!isKeyPressed && Keyboard.isKeyDown(Keyboard.KEY_L))
			{
				isKeyPressed = true;
				DummyData a0 = new DummyData("username",player.username);
				DataStorage.addDataToString(a0);
				String dataString = DataStorage.getDataString();
				PacketDispatcher.sendPacketToServer(TAPacketHandler.getPacketFor("TA.Button", dataString));
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(isKeyPressed && !Keyboard.isKeyDown(Keyboard.KEY_L))
		{
			isKeyPressed = false;
		}
		if(isJumpPressed && !Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode))
		{
			isJumpPressed = false;
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "terraarts";
	}

}
