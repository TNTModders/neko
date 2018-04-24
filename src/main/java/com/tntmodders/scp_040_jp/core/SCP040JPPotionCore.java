package com.tntmodders.scp_040_jp.core;

import com.tntmodders.scp_040_jp.SCP040JPCore;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.IForgeRegistry;

public class SCP040JPPotionCore {
    public static final Potion NEKO = new PotionNeko();

    public static void register(IForgeRegistry<Potion> registry) {
        registry.register(NEKO);
    }

    private static class PotionNeko extends Potion {
        protected PotionNeko() {
            super(true, 0xffffff);
            this.setRegistryName(SCP040JPCore.MODID, "effect_neko");
            this.setPotionName("scp_040_jp.effect_neko");
        }
    }
}
