package TA.Common.Block;

import DummyCore.Core.CoreInitialiser;
import DummyCore.Items.MultiItem;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import TA.Common.Tile.TileEntityTAChest;
import TA.Mod.TerraArts;
import TA.Network.TAPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.minecraft.block.Block;
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

public class BlockTAChest extends BlockContainer
{
	public static String[] names = {"Iron","Gold","Diamond","Gem","Darkness"};
	public int type;
    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final Random furnaceRand = new Random();

    /** True if this is an active furnace, false if idle */
    private boolean isActive = false;
    @SideOnly(Side.CLIENT)
    private Icon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private Icon furnaceIconFront;

    public BlockTAChest(String par1, int rarity)
    {
        super(TerraArts.cfg.getIdForBlock(par1), Material.iron);
        type = rarity;
    }

    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
        
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != par2 ? this.blockIcon : this.furnaceIconFront));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("terraarts:chestAnySide");
        this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "terraarts:chest"+names[this.type]+"Lock" : "terraarts:chest"+names[this.type]+"Lock");
        this.furnaceIconTop = par1IconRegister.registerIcon("terraarts:chestTop");
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
        	boolean shouldOpen = false;
        	ItemStack heldStack = par5EntityPlayer.getCurrentEquippedItem();
        	if(heldStack != null)
        	{
        		int id = heldStack.itemID;
        		if(id == CoreInitialiser.mItem.itemID)
        		{
        			String metaString = MultiItem.getUnlocalisedNameByMetadata(heldStack.getItemDamage());
        			String subMetaString = metaString.substring(3, metaString.length());
        			if(subMetaString.equals(names[type]))
        			{
        				shouldOpen = true;
        			}
        		}
        	}
        	if(shouldOpen)
        	{
        		par5EntityPlayer.openGui(TerraArts.instance, 374436, par1World, par2, par3, par4);
        		TAPacketHandler.playSoundOnServer("random.door_close", par2+0.5D, par3+0.5D, par4+0.5D, 1, 0.2D, 16, par5EntityPlayer.dimension);
        	}
        	else
        	{
        		par5EntityPlayer.addChatMessage("This chest seems to be locked with some kind of magic");
        		TAPacketHandler.playSoundOnServer("random.door_open", par2+0.5D, par3+0.5D, par4+0.5D, 1, 2D, 16, par5EntityPlayer.dimension);
        		TAPacketHandler.playSoundOnServer("random.door_close", par2+0.5D, par3+0.5D, par4+0.5D, 1, 2D, 16, par5EntityPlayer.dimension);
        	}
            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityTAChest();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack chestDropStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }
        TileEntityTAChest chest = (TileEntityTAChest) par1World.getBlockTileEntity(par2, par3, par4);
        NBTTagCompound dropTag = MiscUtils.getStackTag(chestDropStack);
        chest.readFromNBT(dropTag);
        chest.xCoord = par2;
        chest.yCoord = par3;
        chest.zCoord = par4;
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	ItemStack chestDropStack = new ItemStack(this.blockID,1,0);
    	NBTTagCompound dropTag = MiscUtils.getStackTag(chestDropStack);
    	TileEntityTAChest chest = (TileEntityTAChest) par1World.getBlockTileEntity(par2, par3, par4);
    	chest.writeToNBT(dropTag);
    	chestDropStack.setTagCompound(dropTag);
    	EntityItem chestItem = new EntityItem(par1World,par2+0.5D,par3+0.5D,par4+0.5D,chestDropStack);
    	chestItem.setPositionAndRotation(par2+0.5D,par3+0.5D,par4+0.5D, 1, 1);
    	chestItem.motionX += MathUtils.randomDouble(par1World.rand);
    	chestItem.motionY += MathUtils.randomDouble(par1World.rand);
    	chestItem.motionZ += MathUtils.randomDouble(par1World.rand);
    	if(!par1World.isRemote)
    		par1World.spawnEntityInWorld(chestItem);
    	TAPacketHandler.playSoundOnServer("fireworks.blast", par2+0.5D, par3+0.5D, par4+0.5D, 1, 0.1D, 16, par1World.provider.dimensionId);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

}
