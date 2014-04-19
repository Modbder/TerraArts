package TA.Mod;

import java.io.IOException;

import net.minecraftforge.common.MinecraftForge;
import TA.Common.Registry.CommonRegistry;
import TA.Network.TAPacketHandler;
import TA.Network.Proxy.CommonProxy;
import TA.Utils.TAConfig;
import TA.Utils.TAEventHandler;
import TA.Utils.TAPlayerTickHandler;
import TA.Utils.TAPlayerTracker;
import DummyCore.Core.Core;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "terraarts",name = "Terra Arts",version = "1.0.164.01d")
@NetworkMod(clientSideRequired = true,serverSideRequired = false,channels = {"TA.Sync","TA.Button","TA.Jump","TA.PJump","TA.JHold","TA.Particle","TA.Sound","TA.Move"},packetHandler = TAPacketHandler.class)
public class TerraArts {
	public static TAConfig cfg;
	@SidedProxy(clientSide = "TA.Network.Proxy.ClientProxy",serverSide = "TA.Network.Proxy.CommonProxy")
	public static CommonProxy proxy;
	public static TerraArts instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		cfg = new TAConfig();
		try {
			instance = this;
			Core.registerModAbsolute(getClass(), "Terra Arts", event.getModConfigurationDirectory().getAbsolutePath(), cfg);
			MinecraftForge.EVENT_BUS.register(new TAEventHandler());
			GameRegistry.registerPlayerTracker(new TAPlayerTracker());
			NetworkRegistry.instance().registerGuiHandler(this, proxy);
			TickRegistry.registerTickHandler(new TAPlayerTickHandler(), Side.SERVER);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		CommonRegistry.register();
		proxy.registerRenderInformation();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
