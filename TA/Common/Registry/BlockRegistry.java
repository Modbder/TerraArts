package TA.Common.Registry;

import DummyCore.Blocks.BlocksRegistry;
import TA.Common.Block.BlockTAChest;
import TA.Common.Block.BlockTinkersBench;
import TA.Mod.TerraArts;
import net.minecraft.block.Block;

public class BlockRegistry {
	public static void register()
	{
		tt = new BlockTinkersBench("tt").setHardness(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("TA.Block.TT");
		chests[0] = new BlockTAChest("ironChest",0).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TA.Block.IronChest");
		chests[1] = new BlockTAChest("goldChest",1).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TA.Block.GoldChest");
		chests[2] = new BlockTAChest("diamondChest",2).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TA.Block.DiamondChest");
		chests[3] = new BlockTAChest("gemChest",3).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TA.Block.GemChest");
		chests[4] = new BlockTAChest("darknessChest",4).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TA.Block.DarknessChest");
		BlocksRegistry.registerBlock(tt, "Tinkerer's Table", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[0], "Iron Chest", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[1], "Gold Chest", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[2], "Diamond Chest", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[3], "Gem Chest", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[4], "Darkness Chest", TerraArts.class, null);
	}
	
	public static Block tt;
	public static Block[] chests = new Block[5];
}
