package com.tntmodders.scp_040_jp.entity;

import com.tntmodders.scp_040_jp.core.SCP040JPPotionCore;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SCP040JPEntityNeko extends EntityOcelot {
    public SCP040JPEntityNeko(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EntityPlayer player = this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 32, false);
        if (player != null) {
            this.setAttackTarget(player);
            this.getLookHelper().setLookPositionWithEntity(player, 1, 1);
            Vec3d vec3d = player.getLook(1.0F).normalize();
            Vec3d vec3d1 = new Vec3d(this.posX - player.posX,
                    this.getEntityBoundingBox().minY + (double) this.getEyeHeight() -
                            (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
            double d0 = vec3d1.lengthVector();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dotProduct(vec3d1);
            if (d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this)) {
                player.addPotionEffect(new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false, false));
            }
        }
    }

    public int getTameSkin() {
        return 0;
    }

    public void setTameSkin(int skinId) {
    }

    @Override
    public boolean getCanSpawnHere() {
        boolean flg = super.getCanSpawnHere() &&
                this.world.loadedEntityList.stream().noneMatch(entity -> entity instanceof SCP040JPEntityNeko);
        return flg;
    }
}
