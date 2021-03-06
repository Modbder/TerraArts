package TA.Common.Item;

import TA.Utils.TAUtils;
import am2.api.IExtendedProperties;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_CelestialStone extends ItemArtifact{

	public ItemArtifact_CelestialStone(String par1) {
		super(par1);
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
			p.addPotionEffect(new PotionEffect(Potion.damageBoost.id,10,0,true));
			p.addPotionEffect(new PotionEffect(Potion.field_76444_x.id,100,0,true));
	}

}
