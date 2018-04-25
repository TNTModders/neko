package com.tntmodders.scp_040_jp.world;

import com.google.common.collect.Lists;
import com.tntmodders.scp_040_jp.SCP040JPCore;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StructureNekoPieces {
    public static void registerVillagePieces() {
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.House1.class, "NeBH");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Field1.class, "NeDF");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Field2.class, "NeF");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Torch.class, "NeL");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Hall.class, "NePH");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.House4Garden.class, "NeSH");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.WoodHut.class, "NeSmH");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Church.class, "NeST");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.House2.class, "NeS");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Start.class, "NeStart");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Path.class, "NeSR");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.House3.class, "NeTRH");
        MapGenStructureIO.registerStructureComponent(StructureNekoPieces.Well.class, "NeW");
    }

    public static List<PieceWeight> getStructureVillageWeightedPieceList(Random random, int size) {
        List<PieceWeight> list = Lists.newArrayList();
        list.add(new PieceWeight(House4Garden.class, 4, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
        list.add(new PieceWeight(Church.class, 20, MathHelper.getInt(random, size, 1 + size)));
        list.add(new PieceWeight(House1.class, 20, MathHelper.getInt(random, size, 2 + size)));
        list.add(new PieceWeight(WoodHut.class, 3, MathHelper.getInt(random, 2 + size, 5 + size * 3)));
        list.add(new PieceWeight(Hall.class, 15, MathHelper.getInt(random, size, 2 + size)));
        list.add(new PieceWeight(Field1.class, 3, MathHelper.getInt(random, 1 + size, 4 + size)));
        list.add(new PieceWeight(Field2.class, 3, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
        list.add(new PieceWeight(House2.class, 15, MathHelper.getInt(random, 0, 1 + size)));
        list.add(new PieceWeight(House3.class, 8, MathHelper.getInt(random, size, 3 + size * 2)));
        //net.minecraftforge.fml.common.registry.VillagerRegistry.addExtraVillageComponents(list, random, size);
        Iterator<PieceWeight> iterator = list.iterator();

        while (iterator.hasNext()) {
            if ((iterator.next()).villagePiecesLimit == 0) {
                iterator.remove();
            }
        }

        return list;
    }

    private static int updatePieceWeight(List<PieceWeight> p_75079_0_) {
        boolean flag = false;
        int i = 0;

        for (PieceWeight StructureNekoPieces$pieceweight : p_75079_0_) {
            if (StructureNekoPieces$pieceweight.villagePiecesLimit > 0 &&
                    StructureNekoPieces$pieceweight.villagePiecesSpawned <
                            StructureNekoPieces$pieceweight.villagePiecesLimit) {
                flag = true;
            }

            i += StructureNekoPieces$pieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }

    private static Village findAndCreateComponentFactory(Start start, PieceWeight weight,
            List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY,
            int structureMinZ, EnumFacing facing, int componentType) {
        Class<? extends Village> oclass = weight.villagePieceClass;
        Village StructureNekoPieces$village = null;

        if (oclass == House4Garden.class) {
            StructureNekoPieces$village =
                    House4Garden.createPiece(start, structureComponents, rand, structureMinX, structureMinY,
                            structureMinZ, facing, componentType);
        } else if (oclass == Church.class) {
            StructureNekoPieces$village =
                    Church.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == House1.class) {
            StructureNekoPieces$village =
                    House1.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == WoodHut.class) {
            StructureNekoPieces$village =
                    WoodHut.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == Hall.class) {
            StructureNekoPieces$village =
                    Hall.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == Field1.class) {
            StructureNekoPieces$village =
                    Field1.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == Field2.class) {
            StructureNekoPieces$village =
                    Field2.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == House2.class) {
            StructureNekoPieces$village =
                    House2.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        } else if (oclass == House3.class) {
            StructureNekoPieces$village =
                    House3.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType);
        }

        return StructureNekoPieces$village;
    }

    private static Village generateComponent(Start start, List<StructureComponent> structureComponents, Random rand,
            int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
        int i = updatePieceWeight(start.structureVillageWeightedPieceList);

        if (i <= 0) {
            return null;
        } else {
            int j = 0;

            while (j < 5) {
                ++j;
                int k = rand.nextInt(i);

                for (PieceWeight StructureNekoPieces$pieceweight : start.structureVillageWeightedPieceList) {
                    k -= StructureNekoPieces$pieceweight.villagePieceWeight;

                    if (k < 0) {
                        if (!StructureNekoPieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType) ||
                                StructureNekoPieces$pieceweight == start.structVillagePieceWeight &&
                                        start.structureVillageWeightedPieceList.size() > 1) {
                            break;
                        }

                        Village StructureNekoPieces$village =
                                findAndCreateComponentFactory(start, StructureNekoPieces$pieceweight,
                                        structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing,
                                        componentType);

                        if (StructureNekoPieces$village != null) {
                            ++StructureNekoPieces$pieceweight.villagePiecesSpawned;
                            start.structVillagePieceWeight = StructureNekoPieces$pieceweight;

                            if (!StructureNekoPieces$pieceweight.canSpawnMoreVillagePieces()) {
                                start.structureVillageWeightedPieceList.remove(StructureNekoPieces$pieceweight);
                            }

                            return StructureNekoPieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox =
                    Torch.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing);

            if (structureboundingbox != null) {
                return new Torch(start, componentType, rand, structureboundingbox, facing);
            } else {
                return null;
            }
        }
    }

    private static StructureComponent generateAndAddComponent(Start start, List<StructureComponent> structureComponents,
            Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing,
            int componentType) {
        if (componentType > 50) {
            return null;
        } else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 &&
                Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112) {
            StructureComponent structurecomponent =
                    generateComponent(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ,
                            facing, componentType + 1);

            if (structurecomponent != null) {
                structureComponents.add(structurecomponent);
                start.pendingHouses.add(structurecomponent);
                return structurecomponent;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static void generateAndAddRoadPiece(Start start, List<StructureComponent> p_176069_1_, Random rand,
            int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_) {
        if (p_176069_7_ > 3 + start.terrainType) {
        } else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 &&
                Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112) {
            StructureBoundingBox structureboundingbox =
                    Path.findPieceBox(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10) {
                StructureComponent structurecomponent =
                        new Path(start, p_176069_7_, rand, structureboundingbox, facing);
                p_176069_1_.add(structurecomponent);
                start.pendingRoads.add(structurecomponent);
            } else {
            }
        } else {
        }
    }

    public static class Church extends Village {
        public Church() {
        }

        public Church(Start start, int type, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45564_4_;
        }

        public static Church createPiece(Start start, List<StructureComponent> p_175854_1_, Random rand,
                int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5,
                            12, 9, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ?
                    new Church(start, p_175854_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
            return 2;
        }
    }

    public static class Field1 extends Village {
        /**
         * First crop type for this field.
         */
        private Block cropTypeA;
        /**
         * Second crop type for this field.
         */
        private Block cropTypeB;
        /**
         * Third crop type for this field.
         */
        private Block cropTypeC;
        /**
         * Fourth crop type for this field.
         */
        private Block cropTypeD;

        public Field1() {
        }

        public Field1(Start start, int type, Random rand, StructureBoundingBox p_i45570_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45570_4_;
            this.cropTypeA = this.getRandomCropType(rand);
            this.cropTypeB = this.getRandomCropType(rand);
            this.cropTypeC = this.getRandomCropType(rand);
            this.cropTypeD = this.getRandomCropType(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
            tagCompound.setInteger("CC", Block.REGISTRY.getIDForObject(this.cropTypeC));
            tagCompound.setInteger("CD", Block.REGISTRY.getIDForObject(this.cropTypeD));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
            this.cropTypeC = Block.getBlockById(tagCompound.getInteger("CC"));
            this.cropTypeD = Block.getBlockById(tagCompound.getInteger("CD"));

            if (!(this.cropTypeA instanceof BlockCrops)) {
                this.cropTypeA = Blocks.WHEAT;
            }

            if (!(this.cropTypeB instanceof BlockCrops)) {
                this.cropTypeB = Blocks.CARROTS;
            }

            if (!(this.cropTypeC instanceof BlockCrops)) {
                this.cropTypeC = Blocks.POTATOES;
            }

            if (!(this.cropTypeD instanceof BlockCrops)) {
                this.cropTypeD = Blocks.BEETROOTS;
            }
        }

        private Block getRandomCropType(Random rand) {
            switch (rand.nextInt(10)) {
                case 0:
                case 1:
                    return Blocks.CARROTS;
                case 2:
                case 3:
                    return Blocks.POTATOES;
                case 4:
                    return Blocks.BEETROOTS;
                default:
                    return Blocks.WHEAT;
            }
        }

        public static Field1 createPiece(Start start, List<StructureComponent> p_175851_1_, Random rand,
                int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing facing, int p_175851_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0,
                            13, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ?
                    new Field1(start, p_175851_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public static class Field2 extends Village {
        /**
         * First crop type for this field.
         */
        private Block cropTypeA;
        /**
         * Second crop type for this field.
         */
        private Block cropTypeB;

        public Field2() {
        }

        public Field2(Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing) {
            super(start, p_i45569_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = this.getRandomCropType(rand);
            this.cropTypeB = this.getRandomCropType(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block getRandomCropType(Random rand) {
            switch (rand.nextInt(10)) {
                case 0:
                case 1:
                    return Blocks.CARROTS;
                case 2:
                case 3:
                    return Blocks.POTATOES;
                case 4:
                    return Blocks.BEETROOTS;
                default:
                    return Blocks.WHEAT;
            }
        }

        public static Field2 createPiece(Start start, List<StructureComponent> p_175852_1_, Random rand,
                int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7,
                            4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ?
                    new Field2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public static class Hall extends Village {
        public Hall() {
        }

        public Hall(Start start, int type, Random rand, StructureBoundingBox p_i45567_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45567_4_;
        }

        public static Hall createPiece(Start start, List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_,
                int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9,
                            7, 11, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ?
                    new Hall(start, p_175857_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
            return villagersSpawnedIn == 0 ? 4 : super.chooseProfession(villagersSpawnedIn, currentVillagerProfession);
        }
    }

    public static class House1 extends Village {
        public House1() {
        }

        public House1(Start start, int type, Random rand, StructureBoundingBox p_i45571_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45571_4_;
        }

        public static House1 createPiece(Start start, List<StructureComponent> p_175850_1_, Random rand,
                int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9,
                            9, 6, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ?
                    new House1(start, p_175850_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
            return 1;
        }
    }

    public static class House2 extends Village {
        private boolean hasMadeChest;

        public House2() {
        }

        public House2(Start start, int type, Random rand, StructureBoundingBox p_i45563_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45563_4_;
        }

        public static House2 createPiece(Start start, List<StructureComponent> p_175855_1_, Random rand,
                int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0,
                            10, 6, 7, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ?
                    new House2(start, p_175855_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Chest", this.hasMadeChest);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.hasMadeChest = tagCompound.getBoolean("Chest");
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
            return 3;
        }
    }

    public static class House3 extends Village {
        public House3() {
        }

        public House3(Start start, int type, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45561_4_;
        }

        public static House3 createPiece(Start start, List<StructureComponent> p_175849_1_, Random rand,
                int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9,
                            7, 12, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ?
                    new House3(start, p_175849_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public static class House4Garden extends Village {
        private boolean isRoofAccessible;

        public House4Garden() {
        }

        public House4Garden(Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_,
                EnumFacing facing) {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Terrace", this.isRoofAccessible);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.isRoofAccessible = tagCompound.getBoolean("Terrace");
        }

        public static House4Garden createPiece(Start start, List<StructureComponent> p_175858_1_, Random rand,
                int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5,
                            6, 5, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null :
                    new House4Garden(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public static class Path extends Road {
        private int length;

        public Path() {
        }

        public Path(Start start, int p_i45562_2_, Random rand, StructureBoundingBox p_i45562_4_, EnumFacing facing) {
            super(start, p_i45562_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45562_4_;
            this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Length", this.length);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.length = tagCompound.getInteger("Length");
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            boolean flag = false;

            for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5)) {
                StructureComponent structurecomponent =
                        this.getNextComponentNN((Start) componentIn, listIn, rand, 0, i);

                if (structurecomponent != null) {
                    i += Math.max(structurecomponent.getBoundingBox().getXSize(),
                            structurecomponent.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5)) {
                StructureComponent structurecomponent1 =
                        this.getNextComponentPP((Start) componentIn, listIn, rand, 0, j);

                if (structurecomponent1 != null) {
                    j += Math.max(structurecomponent1.getBoundingBox().getXSize(),
                            structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            EnumFacing enumfacing = this.getCoordBaseMode();

            if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX - 1,
                                this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                        break;
                    case SOUTH:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX - 1,
                                this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST,
                                this.getComponentType());
                        break;
                    case WEST:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX,
                                this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH,
                                this.getComponentType());
                        break;
                    case EAST:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.maxX - 2,
                                this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH,
                                this.getComponentType());
                }
            }

            if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.maxX + 1,
                                this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                        break;
                    case SOUTH:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.maxX + 1,
                                this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST,
                                this.getComponentType());
                        break;
                    case WEST:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX,
                                this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH,
                                this.getComponentType());
                        break;
                    case EAST:
                        generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.maxX - 2,
                                this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH,
                                this.getComponentType());
                }
            }
        }

        public static StructureBoundingBox findPieceBox(Start start, List<StructureComponent> p_175848_1_, Random rand,
                int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing) {
            for (int i = 7 * MathHelper.getInt(rand, 3, 5); i >= 7; i -= 7) {
                StructureBoundingBox structureboundingbox =
                        StructureBoundingBox.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0,
                                0, 3, 3, i, facing);

                if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null) {
                    return structureboundingbox;
                }
            }

            return null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public static class PieceWeight {
        public Class<? extends Village> villagePieceClass;
        public final int villagePieceWeight;
        public int villagePiecesSpawned;
        public int villagePiecesLimit;

        public PieceWeight(Class<? extends Village> p_i2098_1_, int p_i2098_2_, int p_i2098_3_) {
            this.villagePieceClass = p_i2098_1_;
            this.villagePieceWeight = p_i2098_2_;
            this.villagePiecesLimit = p_i2098_3_;
        }

        public boolean canSpawnMoreVillagePiecesOfType(int componentType) {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }

        public boolean canSpawnMoreVillagePieces() {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }
    }

    public abstract static class Road extends Village {
        public Road() {
        }

        protected Road(Start start, int type) {
            super(start, type);
        }
    }

    public static class Start extends Well {
        public BiomeProvider worldChunkMngr;
        /**
         * World terrain type, 0 for normal, 1 for flap map
         */
        public int terrainType;
        public PieceWeight structVillagePieceWeight;
        /**
         * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they
         * are removed from this list
         */
        public List<PieceWeight> structureVillageWeightedPieceList;
        public List<StructureComponent> pendingHouses = Lists.newArrayList();
        public List<StructureComponent> pendingRoads = Lists.newArrayList();
        public Biome biome;

        public Start() {
        }

        public Start(BiomeProvider chunkManagerIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_,
                List<PieceWeight> p_i2104_6_, int p_i2104_7_) {
            super(null, 0, rand, p_i2104_4_, p_i2104_5_);
            this.worldChunkMngr = chunkManagerIn;
            this.structureVillageWeightedPieceList = p_i2104_6_;
            this.terrainType = p_i2104_7_;
            Biome biome = chunkManagerIn.getBiome(new BlockPos(p_i2104_4_, 0, p_i2104_5_), Biomes.DEFAULT);
            this.biome = biome;
            this.startPiece = this;

            if (biome instanceof BiomeDesert) {
                this.structureType = 1;
            } else if (biome instanceof BiomeSavanna) {
                this.structureType = 2;
            } else if (biome instanceof BiomeTaiga) {
                this.structureType = 3;
            }

            this.setStructureType(this.structureType);
            this.isZombieInfested = rand.nextInt(50) == 0;
        }
    }

    public static class Torch extends Village {
        public Torch() {
        }

        public Torch(Start start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing) {
            super(start, p_i45568_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45568_4_;
        }

        public static StructureBoundingBox findPieceBox(Start start, List<StructureComponent> p_175856_1_, Random rand,
                int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3,
                            4, 2, facing);
            return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null :
                    structureboundingbox;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }

    public abstract static class Village extends StructureComponent {
        protected int averageGroundLvl = -1;
        /**
         * The number of villagers that have been spawned in this component.
         */
        private int villagersSpawned;
        protected int structureType;
        protected boolean isZombieInfested;
        protected Start startPiece;

        public Village() {
        }

        protected Village(Start start, int type) {
            super(type);

            if (start != null) {
                this.structureType = start.structureType;
                this.isZombieInfested = start.isZombieInfested;
                startPiece = start;
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            tagCompound.setInteger("HPos", this.averageGroundLvl);
            tagCompound.setInteger("VCount", this.villagersSpawned);
            tagCompound.setByte("Type", (byte) this.structureType);
            tagCompound.setBoolean("Zombie", this.isZombieInfested);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            this.averageGroundLvl = tagCompound.getInteger("HPos");
            this.villagersSpawned = tagCompound.getInteger("VCount");
            this.structureType = tagCompound.getByte("Type");

            if (tagCompound.getBoolean("Desert")) {
                this.structureType = 1;
            }

            this.isZombieInfested = tagCompound.getBoolean("Zombie");
        }

        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        @Nullable
        protected StructureComponent getNextComponentNN(Start start, List<StructureComponent> structureComponents,
                Random rand, int p_74891_4_, int p_74891_5_) {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        return generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1,
                                this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST,
                                this.getComponentType());
                    case SOUTH:
                        return generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1,
                                this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST,
                                this.getComponentType());
                    case WEST:
                        return generateAndAddComponent(start, structureComponents, rand,
                                this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_,
                                this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case EAST:
                        return generateAndAddComponent(start, structureComponents, rand,
                                this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_,
                                this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            } else {
                return null;
            }
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        @Nullable
        protected StructureComponent getNextComponentPP(Start start, List<StructureComponent> structureComponents,
                Random rand, int p_74894_4_, int p_74894_5_) {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        return generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1,
                                this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST,
                                this.getComponentType());
                    case SOUTH:
                        return generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1,
                                this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST,
                                this.getComponentType());
                    case WEST:
                        return generateAndAddComponent(start, structureComponents, rand,
                                this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_,
                                this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case EAST:
                        return generateAndAddComponent(start, structureComponents, rand,
                                this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_,
                                this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            } else {
                return null;
            }
        }

        /**
         * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
         * all the levels in the BB's horizontal rectangle).
         */
        protected int getAverageGroundLevel(World worldIn, StructureBoundingBox structurebb) {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
                    blockpos$mutableblockpos.setPos(l, 64, k);

                    if (structurebb.isVecInside(blockpos$mutableblockpos)) {
                        i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(),
                                worldIn.provider.getAverageGroundLevel() - 1);
                        ++j;
                    }
                }
            }

            if (j == 0) {
                return -1;
            } else {
                return i / j;
            }
        }

        protected static boolean canVillageGoDeeper(StructureBoundingBox structurebb) {
            return structurebb != null && structurebb.minY > 10;
        }

        /**
         * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y
         * offset, z offset, number of villagers
         */
        protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count) {
        }

        @Deprecated // Use Forge version below.
        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
            return currentVillagerProfession;
        }

        protected net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession chooseForgeProfession(
                int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof) {
            return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(
                    chooseProfession(count, net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof)));
        }

        protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn) {
            net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event =
                    new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(
                            startPiece == null ? null : startPiece.biome, blockstateIn);
            net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
            if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) {
                return event.getReplacement();
            }
            if (this.structureType == 1) {
                if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                    return Blocks.SANDSTONE.getDefaultState();
                }

                if (blockstateIn.getBlock() == Blocks.COBBLESTONE) {
                    return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
                }

                if (blockstateIn.getBlock() == Blocks.PLANKS) {
                    return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
                }

                if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                    return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
                            blockstateIn.getValue(BlockStairs.FACING));
                }

                if (blockstateIn.getBlock() == Blocks.STONE_STAIRS) {
                    return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
                            blockstateIn.getValue(BlockStairs.FACING));
                }

                if (blockstateIn.getBlock() == Blocks.GRAVEL) {
                    return Blocks.SANDSTONE.getDefaultState();
                }
            } else if (this.structureType == 3) {
                if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                    return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT,
                            BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS,
                            blockstateIn.getValue(BlockLog.LOG_AXIS));
                }

                if (blockstateIn.getBlock() == Blocks.PLANKS) {
                    return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT,
                            BlockPlanks.EnumType.SPRUCE);
                }

                if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                    return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
                            blockstateIn.getValue(BlockStairs.FACING));
                }

                if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                    return Blocks.SPRUCE_FENCE.getDefaultState();
                }
            } else if (this.structureType == 2) {
                if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                    return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT,
                            BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS,
                            blockstateIn.getValue(BlockLog.LOG_AXIS));
                }

                if (blockstateIn.getBlock() == Blocks.PLANKS) {
                    return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT,
                            BlockPlanks.EnumType.ACACIA);
                }

                if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                    return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
                            blockstateIn.getValue(BlockStairs.FACING));
                }

                if (blockstateIn.getBlock() == Blocks.COBBLESTONE) {
                    return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT,
                            BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
                }

                if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                    return Blocks.ACACIA_FENCE.getDefaultState();
                }
            }

            return blockstateIn;
        }

        protected BlockDoor biomeDoor() {
            switch (this.structureType) {
                case 2:
                    return Blocks.ACACIA_DOOR;
                case 3:
                    return Blocks.SPRUCE_DOOR;
                default:
                    return Blocks.OAK_DOOR;
            }
        }

        protected void createVillageDoor(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_,
                int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_) {
            if (!this.isZombieInfested) {
                this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_,
                        EnumFacing.NORTH, this.biomeDoor());
            }
        }

        protected void placeTorch(World p_189926_1_, EnumFacing p_189926_2_, int p_189926_3_, int p_189926_4_,
                int p_189926_5_, StructureBoundingBox p_189926_6_) {
            if (!this.isZombieInfested) {
                this.setBlockState(p_189926_1_,
                        Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, p_189926_2_), p_189926_3_,
                        p_189926_4_, p_189926_5_, p_189926_6_);
            }
        }

        /**
         * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or
         * liquid
         */
        protected void replaceAirAndLiquidDownwards(World worldIn, IBlockState blockstateIn, int x, int y, int z,
                StructureBoundingBox boundingboxIn) {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
            super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
        }

        protected void setStructureType(int p_189924_1_) {
            this.structureType = p_189924_1_;
        }
    }

    public static class Well extends Village {
        public Well() {
        }

        public Well(Start start, int type, Random rand, int x, int z) {
            super(start, type);
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
            } else {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
            }
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX - 1,
                    this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.maxX + 1,
                    this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX + 1,
                    this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            generateAndAddRoadPiece((Start) componentIn, listIn, rand, this.boundingBox.minX + 1,
                    this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
            BlockPos blockpos =
                    new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(12), this.getZWithOffset(0, 0));
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0) {
                    return true;
                }
                this.boundingBox.offset(0, -65, 0);
            }
            int y = this.averageGroundLvl - this.boundingBox.maxY + 3;
            SCP040JPCore.LOGGER.info(blockpos.up(y));
            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, y + 12, 4, iblockstate,
                    SCP040JPCore.NEKOBLOCK.getDefaultState(), false);
            worldIn.setBlockState(new BlockPos(this.getXWithOffset(0, 0) + 2, 0, this.getZWithOffset(0, 0) + 2),
                    SCP040JPCore.NEKOBLOCK.getDefaultState());
            worldIn.setBlockState(new BlockPos(this.getXWithOffset(0, 0) + 3, 0, this.getZWithOffset(0, 0) + 2),
                    SCP040JPCore.NEKOBLOCK.getDefaultState());
            worldIn.setBlockState(new BlockPos(this.getXWithOffset(0, 0) + 2, 0, this.getZWithOffset(0, 0) + 3),
                    SCP040JPCore.NEKOBLOCK.getDefaultState());
            worldIn.setBlockState(new BlockPos(this.getXWithOffset(0, 0) + 3, 0, this.getZWithOffset(0, 0) + 3),
                    SCP040JPCore.NEKOBLOCK.getDefaultState());
            this.setBlockState(worldIn, SCP040JPCore.NEKOBLOCK.getDefaultState(), 2, y + 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, SCP040JPCore.NEKOBLOCK.getDefaultState(), 3, y + 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, SCP040JPCore.NEKOBLOCK.getDefaultState(), 2, y + 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, SCP040JPCore.NEKOBLOCK.getDefaultState(), 3, y + 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, y + 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, y + 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, y + 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, y + 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, y + 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, y + 14, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, y + 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, y + 14, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, y + 15, 1, 4, y + 15, 4, iblockstate, iblockstate,
                    false);

            for (int i = 0; i <= 5; ++i) {
                for (int j = 0; j <= 5; ++j) {
                    if (j == 0 || j == 5 || i == 0 || i == 5) {
                        this.setBlockState(worldIn, iblockstate, j, y + 11, i, structureBoundingBoxIn);
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, y + 12, i, structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }
    }

    public static class WoodHut extends Village {
        private boolean isTallHouse;
        private int tablePosition;

        public WoodHut() {
        }

        public WoodHut(Start start, int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing) {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = structurebb;
            this.isTallHouse = rand.nextBoolean();
            this.tablePosition = rand.nextInt(3);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("T", this.tablePosition);
            tagCompound.setBoolean("C", this.isTallHouse);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.tablePosition = tagCompound.getInteger("T");
            this.isTallHouse = tagCompound.getBoolean("C");
        }

        public static WoodHut createPiece(Start start, List<StructureComponent> p_175853_1_, Random rand,
                int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_) {
            StructureBoundingBox structureboundingbox =
                    StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4,
                            6, 5, facing);
            return canVillageGoDeeper(structureboundingbox) &&
                    StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ?
                    new WoodHut(start, p_175853_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

            return true;
        }
    }
}