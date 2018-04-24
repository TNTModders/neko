package com.tntmodders.scp_040_jp.block;

import com.tntmodders.scp_040_jp.SCP040JPCore;
import com.tntmodders.scp_040_jp.core.SCP040JPPotionCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SCP040JPBlockNeko extends Block {
    public SCP040JPBlockNeko() {
        super(Material.ROCK);
        this.setRegistryName(SCP040JPCore.MODID, "block_neko");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setUnlocalizedName("block_neko");
        this.setHardness(10f);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        playerIn.addPotionEffect(new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false, false));
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
