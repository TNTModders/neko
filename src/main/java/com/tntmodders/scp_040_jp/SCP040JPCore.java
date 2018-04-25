package com.tntmodders.scp_040_jp;

import com.google.common.collect.Lists;
import com.tntmodders.scp_040_jp.block.SCP040JPBlockNeko;
import com.tntmodders.scp_040_jp.core.SCP040JPClientCore;
import com.tntmodders.scp_040_jp.core.SCP040JPModInfo;
import com.tntmodders.scp_040_jp.core.SCP040JPPotionCore;
import com.tntmodders.scp_040_jp.entity.SCP040JPEntityNeko;
import com.tntmodders.scp_040_jp.world.SCP040JPMapGenNeko;
import com.tntmodders.scp_040_jp.world.StructureNekoPieces;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;


@Mod(modid = SCP040JPCore.MODID, version = SCP040JPCore.VERSION, acceptedMinecraftVersions = "[1.12.2]", name = SCP040JPCore.MODID, updateJSON = "https://raw.githubusercontent.com/TNTModders/neko/master/version/version.json")
public class SCP040JPCore {

    //初期設定
    public static final String MODID = "scp_040_jp";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    @Instance(MODID)
    public static SCP040JPCore Instance;
    @Metadata(MODID)
    public static ModMetadata metadata;

    public static final Block NEKOBLOCK = new SCP040JPBlockNeko();


    @EventHandler
    public void construct(FMLConstructionEvent event) {
        SCP040JPModInfo.load(metadata);
        MinecraftForge.EVENT_BUS.register(this);
        MapGenStructureIO.registerStructure(SCP040JPMapGenNeko.Start.class, "structure_neko");
        StructureNekoPieces.registerVillagePieces();
    }

    @SubscribeEvent
    public void registerEntities(Register<EntityEntry> event) {
        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "neko"), SCP040JPEntityNeko.class, "neko", 0, this,
                64, 2, true, 0xffffff, 0x444444);
        EntityRegistry.addSpawn(SCP040JPEntityNeko.class, 1, 1, 1, EnumCreatureType.AMBIENT,
                Lists.newArrayList(Biome.REGISTRY.iterator()).toArray(new Biome[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(NEKOBLOCK), 0,
                new ModelResourceLocation(new ResourceLocation(MODID, "block_neko"), "inventory"));
        SCP040JPClientCore.register();
    }

    @SubscribeEvent
    public void registerBlocks(Register<Block> event) {
        event.getRegistry().register(NEKOBLOCK);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(NEKOBLOCK).setRegistryName(MODID, "block_neko"));
    }


    @SubscribeEvent
    public void registerPotions(Register<Potion> event) {
        SCP040JPPotionCore.register(event.getRegistry());
    }


    @SubscribeEvent
    public void onUpdate(LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            if (event.getEntityLiving().getActivePotionEffect(SCP040JPPotionCore.NEKO) != null &&
                    !event.getEntityLiving().world.isRemote) {
                event.getEntityLiving().world.getEntities(EntityOcelot.class,
                        input -> input.getDistanceSqToEntity(event.getEntityLiving()) < 256).forEach(entityOcelot -> {
                    if (!(entityOcelot instanceof SCP040JPEntityNeko)) {
                        SCP040JPEntityNeko neko = new SCP040JPEntityNeko(entityOcelot.world);
                        neko.copyLocationAndAnglesFrom(entityOcelot);
                        entityOcelot.world.spawnEntity(neko);
                        entityOcelot.setDead();
                    }
                });
            }
            if (FMLCommonHandler.instance().getSide().isClient()) {
                RayTraceResult rayTraceResult = event.getEntityLiving().rayTrace(16, 1.0f);
                if (event.getEntityLiving().world.getBlockState(rayTraceResult.getBlockPos()).getBlock() == NEKOBLOCK &&
                        event.getEntityLiving().getActivePotionEffect(SCP040JPPotionCore.NEKO) == null) {
                    event.getEntityLiving().addPotionEffect(
                            new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false, false));
                    if (event.getEntityLiving() instanceof EntityPlayerSP) {
                        ((EntityPlayerSP) event.getEntityLiving()).sendChatMessage("ねこです。ここにねこがいます。");
                    }
                }
            }
            if (event.getEntityLiving().getActivePotionEffect(SCP040JPPotionCore.NEKO) != null &&
                    event.getEntityLiving().world.getWorldTime() % 100 == 0) {
                if (!event.getEntityLiving().world.isRemote) {
                    for (int i = 0; i < 20; i++) {
                        BlockPos pos = new BlockPos(event.getEntityLiving().posX +
                                MathHelper.getInt(event.getEntityLiving().getRNG(), -4, 4),
                                event.getEntityLiving().posY +
                                        MathHelper.getInt(event.getEntityLiving().getRNG(), -4, 4),
                                event.getEntityLiving().posZ +
                                        MathHelper.getInt(event.getEntityLiving().getRNG(), -4, 4));
                        if (event.getEntityLiving().world.getLight(pos) < 8 &&
                                event.getEntityLiving().world.isAirBlock(pos) &&
                                event.getEntityLiving().world.getBlockState(pos.down()).isNormalCube()) {
                            SCP040JPEntityNeko neko = new SCP040JPEntityNeko(event.getEntityLiving().world);
                            neko.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                            event.getEntityLiving().world.spawnEntity(neko);
                        }
                    }
                }
                if (event.getEntityLiving().world.getWorldTime() % 1000 == 0 &&
                        event.getEntityLiving().world.rand.nextBoolean() &&
                        FMLCommonHandler.instance().getSide().isClient()) {
                    Minecraft.getMinecraft().getToastGui().add(
                            new SystemToast(SystemToast.Type.NARRATOR_TOGGLE, new TextComponentString("ねこです。"),
                                    new TextComponentString(
                                            event.getEntityLiving().getRNG().nextBoolean() ? "よろしくおねがいします。" :
                                                    "ねこはいます。")));
                }
            }
        }
    }

    @SubscribeEvent
    public void onChunckPopulate(PopulateChunkEvent.Pre event) {
        if (event.getWorld().provider.getDimensionType() == DimensionType.OVERWORLD) {
            SCP040JPMapGenNeko genNeko;
            genNeko = new SCP040JPMapGenNeko();
            genNeko.generate(event.getWorld(), event.getChunkX(), event.getChunkZ(), null);
            genNeko.generateStructure(event.getWorld(), event.getRand(),
                    new ChunkPos(event.getChunkX(), event.getChunkZ()));
        }
    }

    @SubscribeEvent
    public void chat(ServerChatEvent event) {
        if (event.getMessage().contains("ねこです")) {
            event.getPlayer().world.playerEntities.forEach(player -> player.addPotionEffect(
                    new PotionEffect(SCP040JPPotionCore.NEKO, Integer.MAX_VALUE, 0, false, false)));
        }
    }
}