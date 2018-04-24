package com.tntmodders.scp_040_jp.core;

import com.tntmodders.scp_040_jp.SCP040JPCore;
import com.tntmodders.scp_040_jp.entity.SCP040JPEntityNeko;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SCP040JPClientCore {
    @SideOnly(Side.CLIENT)
    public static void register() {
        RenderingRegistry.registerEntityRenderingHandler(SCP040JPEntityNeko.class, SCP040JPRenderNeko ::new);
    }

    public static class SCP040JPRenderNeko extends RenderOcelot {

        private static final ResourceLocation LOCATION = new ResourceLocation(SCP040JPCore.MODID,
                "textures/entity/neko.png");


        public SCP040JPRenderNeko(RenderManager p_i47199_1_) {
            super(p_i47199_1_);
        }

        @Override
        protected ResourceLocation getEntityTexture(EntityOcelot entity) {
            return LOCATION;
        }
    }
}
