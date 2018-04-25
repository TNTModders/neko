package com.tntmodders.scp_040_jp.block;

import com.tntmodders.scp_040_jp.SCP040JPCore;
import com.tntmodders.scp_040_jp.core.SCP040JPPotionCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class SCP040JPBlockNeko extends Block {
    public SCP040JPBlockNeko() {
        super(Material.ROCK);
        this.setRegistryName(SCP040JPCore.MODID, "block_neko");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setUnlocalizedName("block_neko");
        this.setHardness(10f);
        this.setTickRandomly(true);
    }

    @Override
    public int tickRate(World worldIn) {
        return 1;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        playerIn.addPotionEffect(new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false, false));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(entityIn instanceof EntityPlayer){
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false,
                    false));
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        if (pos.getY() == 1) {
            worldIn.setBlockToAir(pos.down());
        }
    }

    @Deprecated
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
            List<AxisAlignedBB> collidingBoxes,
            @Nullable
                    Entity entityIn, boolean p_185477_7_) {
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }


}
