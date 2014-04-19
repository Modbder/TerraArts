package TA.Common.Block;

import DummyCore.Utils.MiscUtils;
import TA.Mod.TerraArts;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTinkersBench extends BlockContainer
{

    @SideOnly(Side.CLIENT)
    private Icon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private Icon furnaceIconFront;

    public BlockTinkersBench(String par1)
    {
        super(TerraArts.cfg.getIdForBlock(par1), Material.wood);
    }


    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != par2 ? this.blockIcon : this.furnaceIconFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("terraarts:ttSide");
        this.furnaceIconFront = par1IconRegister.registerIcon("terraarts:ttSide");
        this.furnaceIconTop = par1IconRegister.registerIcon("terraarts:ttTop");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            return true;
        }
    }

    public TileEntity createNewTileEntity(World par1World)
    {
        return null;
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	MiscUtils.dropItemsOnBlockBreak(par1World, par2, par3, par4, par5, par6);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
