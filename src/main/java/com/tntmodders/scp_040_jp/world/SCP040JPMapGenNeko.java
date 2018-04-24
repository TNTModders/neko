package com.tntmodders.scp_040_jp.world;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class SCP040JPMapGenNeko extends MapGenStructure {
    /**
     * A list of all the biomes villages can spawn in.
     */
    public static List<Biome> VILLAGE_SPAWN_BIOMES =
            Arrays.asList(Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA);
    /**
     * None
     */
    private int size;
    private int distance;

    public SCP040JPMapGenNeko() {
        this.distance = 32;
    }

    public SCP040JPMapGenNeko(Map<String, String> map) {
        this();

        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("size")) {
                this.size = MathHelper.getInt(entry.getValue(), this.size, 0);
            } else if (entry.getKey().equals("distance")) {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }

    @Override
    public String getStructureName() {
        return "structure_neko";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.distance - 1;
        }

        for (int t = 0; t < 5; t++) {
            int k = chunkX / this.distance;
            int l = chunkZ / this.distance;
            Random random = this.world.setRandomSeed(k, l, 10387310 + 25565);
            k = k * this.distance;
            l = l * this.distance;
            k = k + random.nextInt(this.distance - 8);
            l = l + random.nextInt(this.distance - 8);

            if (i == k && j == l) {
                return true;
            }
        }

        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 10387312, false, 100,
                findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new SCP040JPMapGenNeko.Start(this.world, this.rand, chunkX, chunkZ, this.size);
    }

    public static class Start extends StructureStart {
        /**
         * well ... thats what it does
         */
        private boolean hasMoreThanTwoComponents;

        public Start() {
        }

        public Start(World worldIn, Random rand, int x, int z, int size) {
            super(x, z);
            List<StructureNekoPieces.PieceWeight> list =
                    StructureNekoPieces.getStructureVillageWeightedPieceList(rand, size);
            StructureNekoPieces.Start structurevillagepieces$start =
                    new StructureNekoPieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list,
                            size);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty()) {
                if (list1.isEmpty()) {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                } else {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components) {
                if (!(structurecomponent1 instanceof StructureNekoPieces.Road)) {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        @Override
        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}

