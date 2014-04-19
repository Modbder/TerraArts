package TA.Common.Registry;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import DummyCore.Core.CoreInitialiser;
import DummyCore.Items.MultiItem;
import TA.Utils.TATradeHandler;
import TA.Utils.TAWorldGen;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class CommonRegistry {
	
	public static void register()
	{
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		TileRegistry.register();
		VillagerRegistry.instance().registerVillagerId(8);
		VillagerRegistry.instance().registerVillagerSkin(8,new ResourceLocation("terraarts","textures/entity/YE3TE2y.png"));
		VillagerRegistry.instance().registerVillageTradeHandler(8, new TATradeHandler());
		EntityVillager.blacksmithSellingList.put(Integer.valueOf(BlockRegistry.tt.blockID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		GameRegistry.registerWorldGenerator(new TAWorldGen());
		GameRegistry.addRecipe(new ItemStack(CoreInitialiser.mItem.itemID,1,MultiItem.getMetadataByName("keyIron")), new Object[]{
			"@@@","@D@",'@',Item.ingotIron,'D',Item.diamond
		});
	}

}
