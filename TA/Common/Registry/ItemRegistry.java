package TA.Common.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import net.minecraft.item.Item;
import DummyCore.Utils.EnumRarityColor;
import TA.API.TAApi;
import TA.Common.Item.ItemArtifact_Aglet;
import TA.Common.Item.ItemArtifact_Ankh;
import TA.Common.Item.ItemArtifact_AnkhShield;
import TA.Common.Item.ItemArtifact_ArcticDivingGear;
import TA.Common.Item.ItemArtifact_BandOfRegen;
import TA.Common.Item.ItemArtifact_BlizzardInABaloon;
import TA.Common.Item.ItemArtifact_BlizzardInABottle;
import TA.Common.Item.ItemArtifact_BracerOfWind;
import TA.Common.Item.ItemArtifact_CelestialStone;
import TA.Common.Item.ItemArtifact_CloudInABaloon;
import TA.Common.Item.ItemArtifact_CloudInABottle;
import TA.Common.Item.ItemArtifact_CobaltShield;
import TA.Common.Item.ItemArtifact_DivingGear;
import TA.Common.Item.ItemArtifact_Flippers;
import TA.Common.Item.ItemArtifact_FluffyBoots;
import TA.Common.Item.ItemArtifact_GoldenCross;
import TA.Common.Item.ItemArtifact_GolemsEye;
import TA.Common.Item.ItemArtifact_HermesBoots;
import TA.Common.Item.ItemArtifact_IseSkates;
import TA.Common.Item.ItemArtifact_JellyfishDivingGear;
import TA.Common.Item.ItemArtifact_JellyfishNeclase;
import TA.Common.Item.ItemArtifact_LavaAmulet;
import TA.Common.Item.ItemArtifact_LavaWalkers;
import TA.Common.Item.ItemArtifact_LuckyEmerald;
import TA.Common.Item.ItemArtifact_LuckyHorseshoe;
import TA.Common.Item.ItemArtifact_MagmaStone;
import TA.Common.Item.ItemArtifact_MoonStone;
import TA.Common.Item.ItemArtifact_ObsidianHorseshoe;
import TA.Common.Item.ItemArtifact_ObsidianShield;
import TA.Common.Item.ItemArtifact_ObsidianSkull;
import TA.Common.Item.ItemArtifact_PaladinsShield;
import TA.Common.Item.ItemArtifact_RocketBoots;
import TA.Common.Item.ItemArtifact_SandstormInABaloon;
import TA.Common.Item.ItemArtifact_SandstormInABottle;
import TA.Common.Item.ItemArtifact_ShinyRedBaloon;
import TA.Common.Item.ItemArtifact_StarCloak;
import TA.Common.Item.ItemArtifact_StarVeil;
import TA.Common.Item.ItemArtifact_SunStone;
import TA.Common.Item.ItemArtifact_TitansGlove;
import TA.Common.Item.ItemArtifact_WaterWalkers;
import TA.Common.Item.ItemArtifact_Wings;
import TA.Mod.TerraArts;

public class ItemRegistry {
	public static void register()
	{
		DummyCore.Items.ItemRegistry.registerMultiItem("keyIron", "Iron Key", "terraarts:ironKey", null, TerraArts.class);
		DummyCore.Items.ItemRegistry.registerMultiItem("keyGold", "Golden Key", "terraarts:goldKey", null, TerraArts.class);
		DummyCore.Items.ItemRegistry.registerMultiItem("keyDiamond", "Diamond Key", "terraarts:diamondKey", null, TerraArts.class);
		DummyCore.Items.ItemRegistry.registerMultiItem("keyGem", "Gemstone Key", "terraarts:gemKey", null, TerraArts.class);
		DummyCore.Items.ItemRegistry.registerMultiItem("keyDarkness", "Dark Key", "terraarts:darknessKey", null, TerraArts.class);
		registerArtifact(ItemArtifact_BandOfRegen.class,"regenBand","Band Of Regeneration","band_of_regeneration",0);
		registerArtifact(ItemArtifact_Aglet.class,"aglet","Aglet","aglet",0);
		registerArtifact(ItemArtifact_IseSkates.class,"iceSkates","Ice Skates","ice_skates",0);
		registerArtifact(ItemArtifact_LuckyHorseshoe.class,"luckyHorseshoe","Lucky HorseShoe","lucky_horseshoe",2);
		registerArtifact(ItemArtifact_Ankh.class,"ankh","Ankh","ankh",4);
		registerArtifact(ItemArtifact_ShinyRedBaloon.class,"baloonRed","Shiny Red Baloon","shiny_red_baloon",1);
		registerArtifact(ItemArtifact_CobaltShield.class,"cobaltShield","Cobalt Shield","cobalt_shield",2);
		registerArtifact(ItemArtifact_CloudInABottle.class,"cloudInABottle","Cloud In A Bottle","cloud_in_a_bottle",3);
		registerArtifact(ItemArtifact_BlizzardInABottle.class,"blizzardInABottle","Blizzard In A Bottle","blizzard_in_a_bottle",3);
		registerArtifact(ItemArtifact_SandstormInABottle.class,"sandstormInABottle","Sandstorm In A Bottle","sandstorm_in_a_bottle",3);
		registerArtifact(ItemArtifact_CloudInABaloon.class,"cloudInABaloon","Cloud In A Baloon","cloud_in_a_baloon",4);
		registerArtifact(ItemArtifact_BlizzardInABaloon.class,"blizzardInABaloon","Blizzard In A Baloon","blizzard_in_a_baloon",4);
		registerArtifact(ItemArtifact_SandstormInABaloon.class,"sandstormInABaloon","Sandstorm In A Baloon","sandstorm_in_a_baloon",4);
		registerArtifact(ItemArtifact_RocketBoots.class,"rocketBoots","Rocket Boots","rocket_boots",3);
		registerArtifact(ItemArtifact_HermesBoots.class,"hermesBoots","Hermes Boots","hermets_boots",1);
		registerArtifact(ItemArtifact_FluffyBoots.class,"fluffyBoots","Fluffy Boots","fluffy_boots",4);
		registerArtifact(ItemArtifact_Wings.class,"wings","Wings","wings_angel",4);
		registerArtifact(ItemArtifact_StarCloak.class,"starCloak","Star Cloak","star_cloak",2);
		registerArtifact(ItemArtifact_TitansGlove.class,"titansGlove","Titan's Glove","titans_glove",2);
		registerArtifact(ItemArtifact_BracerOfWind.class,"windBracer","Bracer Of The Wind","bracer_of_the_wind",1);
		registerArtifact(ItemArtifact_GoldenCross.class,"goldenCross","Golden Cross","golden_cross",2);
		registerArtifact(ItemArtifact_StarVeil.class,"starVeil","Star Veil","star_veil",-1);	
		registerArtifact(ItemArtifact_WaterWalkers.class,"waterWalkers","Water Walkers","water_walkers",2);	
		registerArtifact(ItemArtifact_LavaWalkers.class,"lavaWalkers","Lava Walkers","lava_walkers",3);	
		registerArtifact(ItemArtifact_ObsidianSkull.class,"obsidianSkull","Obsidian Skull","obsidian_skull",0);	
		registerArtifact(ItemArtifact_ObsidianHorseshoe.class,"obsidianHorseshoe","Obsidian Horseshoe","obsidian_horseshoe",3);	
		registerArtifact(ItemArtifact_ObsidianShield.class,"obsidianShield","Obsidian Shield","obsidian_shield",3);	
		registerArtifact(ItemArtifact_AnkhShield.class,"ankhShield","Ankh Shield","ankh_shield",4);
		registerArtifact(ItemArtifact_Flippers.class,"flippers","Flippers","swimmers",1);
		registerArtifact(ItemArtifact_DivingGear.class,"divingGear","Diving Gear","diving_gear",1);
		registerArtifact(ItemArtifact_JellyfishNeclase.class,"jellyfishsNeclase","Jellyfish's Neclase","jellyfishs_neclase",2);
		registerArtifact(ItemArtifact_JellyfishDivingGear.class,"jellyfishsDG","Jellyfish's Diving Gear","jellyfish_diving_gear",3);
		registerArtifact(ItemArtifact_ArcticDivingGear.class,"ADG","Arctic Diving Gear","arctic_diving_gear",4);
		registerArtifact(ItemArtifact_LavaAmulet.class,"lavaAmulet","Lava Amulet","lava_amulet",3);
		registerArtifact(ItemArtifact_GolemsEye.class,"golemsEye","Golem's Eye","golems_eye",3);
		registerArtifact(ItemArtifact_SunStone.class,"sunStone","Sun Stone","sun_stone",3);
		registerArtifact(ItemArtifact_MoonStone.class,"moonStone","Moon Stone","moon_stone",3);
		registerArtifact(ItemArtifact_CelestialStone.class,"celestialStone","Celestial Stone","celestial_stone",4);
		registerArtifact(ItemArtifact_PaladinsShield.class,"paladinsShield","Paladin's Shield","paladins_shield",4);
		registerArtifact(ItemArtifact_MagmaStone.class,"magmaStone","Magmatic Stone","magma_stone",2);
		registerArtifact(ItemArtifact_LuckyEmerald.class,"luckyEmerald","Lucky Emerald","lucky_emerald",3);
	}
	
	public static void registerArtifact(Class artifactClass, String codeName, String inGameName, String textureName, int rarity)
	{
		try {
			EnumRarityColor[] rarityColor = {EnumRarityColor.GOOD,EnumRarityColor.RARE,EnumRarityColor.UNIQUE,EnumRarityColor.LEGENDARY,EnumRarityColor.ULTIMATE};
			art[artIndex] = (Item)artifactClass.getConstructors()[0].newInstance(codeName);
			art[artIndex].setUnlocalizedName(codeName).setMaxStackSize(1).setMaxDamage(0).setTextureName("terraarts:arts/"+textureName);
			DummyCore.Items.ItemRegistry.registerItem(art[artIndex], rarityColor[rarity].getRarityColor()+inGameName, TerraArts.class);
			TAApi.itemsNamesByID.add(codeName);
			TAApi.itemsByNames.put(codeName, art[artIndex]);
			if(TAApi.rarityLists[rarity] == null)
				TAApi.rarityLists[rarity] = new ArrayList();
			TAApi.rarityLists[rarity].add(art[artIndex]);
			++artIndex;
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public static Item[] art = new Item[128];
	public static int artIndex = 0;
}
