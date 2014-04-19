package TA.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import TA.API.TAApi;
import TA.Common.Block.BlockTAChest;
import TA.Common.Registry.BlockRegistry;
import TA.Common.Tile.TileEntityTAChest;
import DummyCore.Core.CoreInitialiser;
import DummyCore.Items.MultiItem;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class TAWorldGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for(int j = 0; j <3; ++j)
		{
			int rarity = 0;
			float floatRarity = random.nextFloat();
			if(floatRarity > 0.5F)
				rarity = 0;
			if(floatRarity <= 0.5F&& floatRarity > 0.2F)
				rarity = 1;
			if(floatRarity <= 0.2F&& floatRarity > 0.1F)
				rarity = 2;
			if(floatRarity <= 0.1F&& floatRarity > 0.03F)
				rarity = 3;
			if(floatRarity <= 0.03F)
				rarity = 4;
			int x = chunkX*16+random.nextInt(16);
			int z = chunkZ*16+random.nextInt(16);
			int y = random.nextInt(128)+6;
			if(MiscUtils.getBlock(world, x, y, z) != null && MiscUtils.getBlock(world, x, y, z).isOpaqueCube())
			{
				if(MiscUtils.getBlock(world, x, y+1, z) == null)
				{
					System.out.println(x+"|"+y+"|"+z+"|"+rarity+"|"+floatRarity);
					for(int dx = -1; dx <= 1; ++dx)
					{
						for(int dz = -1; dz <= 1; ++dz)
						{
							world.setBlock(x+dx, y, z+dz, Block.planks.blockID,0,2);
						}
					}
					world.setBlock(x, y+1, z, BlockRegistry.chests[rarity].blockID,random.nextInt(4),2);
					TileEntityTAChest chest = (TileEntityTAChest) world.getBlockTileEntity(x, y+1, z);
					if(chest == null)
					{
						chest = new TileEntityTAChest();
						chest.xCoord = x;
						chest.yCoord = y+1;
						chest.zCoord = z;
						world.setBlockTileEntity(x, y+1, z, chest);
					}
					int artID = TAApi.rarityLists[rarity].get(random.nextInt(TAApi.rarityLists[rarity].size())).itemID;
					ItemStack artStack = new ItemStack(artID,1,0);
                    ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    WeightedRandomChestContent.generateChestContents(random, info.getItems(random), chest, info.getCount(random));
					chest.setInventorySlotContents(0, artStack);
					if(rarity < 4 && random.nextFloat() < 0.4F)
					{
						ItemStack keyStack = new ItemStack(CoreInitialiser.mItem.itemID,1,MultiItem.getMetadataByName("key"+BlockTAChest.names[rarity+1]));
						chest.setInventorySlotContents(1, keyStack);
					}
				}
			}
		}
	}

}
