package com.fizix.birdsneststres;


import com.fizix.birdsneststres.setup.ItemListManager;
import net.minecraft.block.Block;


import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class HarvestTreasureEventHandler
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static Boolean isSubmerged = false;

    @SubscribeEvent
    public void onDrops(BlockEvent.BreakEvent event)
    {
        int rarityToUse = 25;
        String blockRegistryName = "";
        String blockBiomeName = "";

        Boolean dropNest = false;
        Boolean dropBanditPouch = false;
        Boolean dropAncientTreasure = false;
        Boolean dropSunkenTreasure = false;

        Block theblock = event.getState().getBlock();
        Random random = new Random();
        BlockPos pos = event.getPos();

        /*
        LOGGER.debug("Tags: " + theblock.getTags());
        LOGGER.debug("Registry Name: " + theblock.getRegistryName());
        LOGGER.debug("Registry Type: " + theblock.getRegistryType());
        LOGGER.debug("Biome: " + event.getWorld().getBiome(pos).getTranslationKey());
        */

        if(event.getPlayer().isInWaterOrBubbleColumn())
        {
            this.isSubmerged = true;
        }
        else
        {
            this.isSubmerged = false;
        }


        /*
        if(theblock.isIn(BlockTags.LEAVES)) { LOGGER.debug("BLOCK TYPE IS LEAVES "); }
        if(theblock.isIn(BlockTags.SAND))   { LOGGER.debug("BLOCK TYPE IS SAND ");   }
        if(theblock.isIn(BlockTags.CORALS))   { LOGGER.debug("BLOCK TYPE IS CORALS ");   }
        if(theblock.isIn(BlockTags.CORAL_BLOCKS))   { LOGGER.debug("BLOCK TYPE IS CORAL_BLOCKS ");   }
        if(theblock.isIn(BlockTags.WALL_CORALS))   { LOGGER.debug("BLOCK TYPE IS WALL_CORALS ");   }
        if(theblock.isIn(BlockTags.CORAL_PLANTS))   { LOGGER.debug("BLOCK TYPE IS CORAL_PLANTS ");   }
        if(theblock.isIn(BlockTags.DIRT_LIKE))   { LOGGER.debug("BLOCK TYPE IS DIRT_LIKE ");   }
        */


        // Get additional block details so we can examine it in more closely
        blockRegistryName = theblock.getRegistryName().toString();
        blockBiomeName = event.getWorld().getBiome(pos).getTranslationKey();



        // Discover the drop type
        dropNest = this.dropDiscovery("nest", theblock,  blockRegistryName, blockBiomeName, Config.NEST_ENABLED.get());
        dropBanditPouch = this.dropDiscovery("bandit_pouch", theblock,  blockRegistryName, blockBiomeName, Config.BANDIT_POUCH_ENABLED.get());
        dropAncientTreasure = this.dropDiscovery("ancient_treasure", theblock,  blockRegistryName, blockBiomeName, Config.ANCIENT_TREASURE_ENABLED.get());
        dropSunkenTreasure = this.dropDiscovery("sunken_treasure", theblock,  blockRegistryName, blockBiomeName, Config.SUNKEN_TREASURE_ENABLED.get());





        // Nest Drops
        if(dropNest == true)
        {
            rarityToUse = this.getNestDropRarity(theblock, blockRegistryName, blockBiomeName);

            this.spawnDrop(new ItemStack(ItemListManager.BIRDSNEST,1), event, pos, rarityToUse);
        }


        // Bandit Treasure Pouch Drops
        if(dropBanditPouch == true)
        {
            rarityToUse = this.getBanditPouchDropRarity(theblock, blockRegistryName, blockBiomeName);

            this.spawnDrop(new ItemStack(ItemListManager.BANDITPOUCH,1), event, pos, rarityToUse);
        }


        // Ancient Treasure Pouch Drops
        if(dropAncientTreasure == true)
        {
            rarityToUse = this.getAncientTreasureDropRarity(theblock, blockRegistryName, blockBiomeName);

            this.spawnDrop(new ItemStack(ItemListManager.ANCIENTTREASURE,1), event, pos, rarityToUse);
        }


        // Sunken Treasure Pouch Drops
        if(dropSunkenTreasure == true)
        {
            rarityToUse = this.getSunkenTreasureDropRarity(theblock, blockRegistryName, blockBiomeName);

            this.spawnDrop(new ItemStack(ItemListManager.SUNKENTREASURE,1), event, pos, rarityToUse);
        }
    }



    // ---------------------------------------------------------------------------------------------------------------------------
    // dropDiscovery
    // ---------------------------------------------------------------------------------------------------------------------------
    // Tells us whether a particular treasure can be dropped here
    // ---------------------------------------------------------------------------------------------------------------------------
    private Boolean dropDiscovery(String dropType, Block inBlock,  String inRegistry, String inBiome, Boolean isEnabled)
    {
        Boolean isActive = false;

        if(dropType == "nest" && isEnabled == true && inBlock.isIn(BlockTags.LEAVES))
        {
            isActive = true;
        }

        if(dropType == "bandit_pouch" && isEnabled == true && (inBlock.isIn(BlockTags.SAND) || inRegistry == "minecraft:snow_block" || inRegistry == "minecraft:grass" || inRegistry == "minecraft:tall_grass" || inRegistry == "biomesoplenty:bush"))
        {
            isActive = true;
        }

        if(dropType == "ancient_treasure" && isEnabled == true && (inBlock.isIn(BlockTags.ICE) || inBlock.isIn(BlockTags.SAND) || inRegistry == "minecraft:snow_block"))
        {
            if(inBlock.isIn(BlockTags.ICE))
            {
                isActive = true;
            }

            if(inRegistry == "minecraft:snow_block")
            {
                if (    inBiome == "biome.minecraft.snowy_tundra" ||
                        inBiome == "biome.minecraft.snowy_mountains" ||
                        inBiome == "biome.minecraft.frozen_ocean" ||
                        inBiome == "biome.minecraft.frozen_river" ||
                        inBiome == "biome.minecraft.deep_frozen_ocean" ||
                        inBiome == "biome.biomesoplenty.alps" ||
                        inBiome == "biome.biomesoplenty.cold_desert" ||
                        inBiome == "biome.biomesoplenty.snowy_coniferous_forest" ||
                        inBiome == "biome.biomesoplenty.snowy_fir_clearing" ||
                        inBiome == "biome.biomesoplenty.snowy_forest" ||
                        inBiome == "biome.biomesoplenty.alps_foothills" ||
                        inBiome == "biome.biomesoplenty.tundra"
                )
                {
                    isActive = true;
                }
            }
            if(inBlock.isIn(BlockTags.SAND))
            {
                if(     inBiome == "biome.minecraft.desert" ||
                        inBiome == "biome.minecraft.desert_hills" ||
                        inBiome == "biome.minecraft.desert_lakes" ||
                        inBiome == "biome.minecraft.desert_lakes" ||
                        inBiome == "biome.biomesoplenty.cold_desert" ||
                        inBiome == "biome.biomesoplenty.oasis" ||
                        inBiome == "biome.biomesoplenty.outback" ||
                        inBiome == "biome.biomesoplenty.dead_reef"
                )
                {
                    isActive = true;
                }

            }
        }


        if(dropType == "sunken_treasure" && isEnabled == true)
        {
            if (inBlock.isIn(BlockTags.CORAL_BLOCKS)) {
                isActive = true;
            }

            if(     inBiome == "biome.minecraft.frozen_ocean" ||
                    inBiome == "biome.minecraft.beach" ||
                    inBiome == "biome.minecraft.deep_ocean" ||
                    inBiome == "biome.minecraft.snowy_beach" ||
                    inBiome == "biome.minecraft.warm_ocean" ||
                    inBiome == "biome.minecraft.lukewarm_ocean" ||
                    inBiome == "biome.minecraft.cold_ocean" ||
                    inBiome == "biome.minecraft.deep_warm_ocean" ||
                    inBiome == "biome.minecraft.deep_lukewarm_ocean" ||
                    inBiome == "biome.minecraft.deep_cold_ocean" ||
                    inBiome == "biome.minecraft.deep_frozen_ocean" ||
                    inBiome == "biome.biomesoplenty.dead_reef"
            )
            {
                if (inBlock.isIn(BlockTags.SAND))
                {
                    isActive = true;
                }
            }
        }

        return isActive;
    }
    // ---------------------------------------------------------------------------------------------------------------------------



    // ---------------------------------------------------------------------------------------------------------------------------
    // getNestDropRarity
    // ---------------------------------------------------------------------------------------------------------------------------
    // Selects the appropriate drop rarity for nests
    // ---------------------------------------------------------------------------------------------------------------------------
    private int getNestDropRarity(Block thisblock, String thisRegistry, String thisBiome)
    {
        int rarity = Config.NEST_DROP_RARITY_NORMAL.get();

        if(     thisBiome == "biome.minecraft.forest" ||
                thisBiome == "biome.minecraft.jungle" ||
                thisBiome == "biome.minecraft.birch_forest" ||
                thisBiome == "biome.minecraft.dark_forest" ||
                thisBiome == "biome.minecraft.giant_tree_taiga" ||
                thisBiome == "biome.minecraft.modified_jungle" ||
                thisBiome == "biome.minecraft.tall_birch_forest" ||
                thisBiome == "biome.minecraft.giant_spruce_taiga" ||
                thisBiome == "biome.minecraft.bamboo_jungle" ||
                thisBiome == "biome.biomesoplenty.cherry_blossom_grove" ||
                thisBiome == "biome.biomesoplenty.coniferous_forest" ||
                thisBiome == "biome.biomesoplenty.dead_forest" ||
                thisBiome == "biome.biomesoplenty.grove" ||
                thisBiome == "biome.biomesoplenty.maple_woods" ||
                thisBiome == "biome.biomesoplenty.mystic_grove" ||
                thisBiome == "biome.biomesoplenty.ominous_woods" ||
                thisBiome == "biome.biomesoplenty.rainforest" ||
                thisBiome == "biome.biomesoplenty.redwood_forest" ||
                thisBiome == "biome.biomesoplenty.seasonal_forest" ||
                thisBiome == "biome.biomesoplenty.snowy_coniferous_forest" ||
                thisBiome == "biome.biomesoplenty.snowy_forest" ||
                thisBiome == "biome.biomesoplenty.temperate_rainforest" ||
                thisBiome == "biome.biomesoplenty.tropical_rainforest" ||
                thisBiome == "biome.biomesoplenty.woodland" ||
                thisBiome == "biome.biomesoplenty.ethereal_forest"

        )
        {
            rarity = Config.NEST_DROP_RARITY_HIGH.get();
        }
        return rarity;
    }
    // ---------------------------------------------------------------------------------------------------------------------------



    // ---------------------------------------------------------------------------------------------------------------------------
    // getBanditPouchDropRarity
    // ---------------------------------------------------------------------------------------------------------------------------
    // Selects the appropriate drop rarity for bandit pouches
    // ---------------------------------------------------------------------------------------------------------------------------
    private int getBanditPouchDropRarity(Block thisblock, String thisRegistry, String thisBiome)
    {
        int rarity = Config.BANDIT_POUCH_DROP_RARITY_NORMAL.get();

        if(thisRegistry == "minecraft:grass" || thisRegistry == "minecraft:tall_grass" || thisRegistry == "biomesoplenty:bush")
        {
            rarity = Config.BANDIT_POUCH_DROP_RARITY_LOW.get();
        }

        if(thisblock.isIn(BlockTags.SAND) || thisRegistry == "minecraft:snow_block")
        {
            rarity = Config.BANDIT_POUCH_DROP_RARITY_LOW.get();
        }


        return rarity;
    }
    // ---------------------------------------------------------------------------------------------------------------------------



    // ---------------------------------------------------------------------------------------------------------------------------
    // getAncientTreasureDropRarity
    // ---------------------------------------------------------------------------------------------------------------------------
    // Selects the appropriate drop rarity for ancient treasure
    // ---------------------------------------------------------------------------------------------------------------------------
    private int getAncientTreasureDropRarity(Block thisblock, String thisRegistry, String thisBiome)
    {
        int rarity = Config.ANCIENT_TREASURE_DROP_RARITY_NORMAL.get();

        if(thisblock.isIn(BlockTags.SAND) || thisRegistry == "minecraft:snow_block")
        {
            rarity = Config.ANCIENT_TREASURE_DROP_RARITY_LOW.get();
        }



        return rarity;
    }
    // ---------------------------------------------------------------------------------------------------------------------------



    // ---------------------------------------------------------------------------------------------------------------------------
    // getSunkenTreasureDropRarity
    // ---------------------------------------------------------------------------------------------------------------------------
    // Selects the appropriate drop rarity for sunken treasure
    // ---------------------------------------------------------------------------------------------------------------------------
    private int getSunkenTreasureDropRarity(Block thisblock, String thisRegistry, String thisBiome)
    {
        int rarity = Config.SUNKEN_TREASURE_DROP_RARITY_NORMAL.get();

        if(thisblock.isIn(BlockTags.SAND))
        {
            rarity = Config.SUNKEN_TREASURE_DROP_RARITY_LOW.get();

            if(this.isSubmerged == true)
            {
                rarity = Config.SUNKEN_TREASURE_DROP_RARITY_NORMAL.get();
            }
        }

        if(thisblock.isIn(BlockTags.CORAL_BLOCKS))
        {
            rarity = Config.SUNKEN_TREASURE_DROP_RARITY_HIGH.get();
        }

        return rarity;
    }
    // ---------------------------------------------------------------------------------------------------------------------------





    // ---------------------------------------------------------------------------------------------------------------------------
    // spawnDrop
    // ---------------------------------------------------------------------------------------------------------------------------
    // Spawns a drop
    // ---------------------------------------------------------------------------------------------------------------------------
    private void spawnDrop(ItemStack thisStack, BlockEvent.BreakEvent thisEvent, BlockPos thisPos, int thisRarity)
    {
        Random random = new Random();

        double d0 = random.nextFloat() * 0.5D +0.25D;
        double d1 = random.nextFloat() * 0.5D +0.25D;
        double d2 = random.nextFloat() * 0.5D +0.25D;



        int randRarity = random.nextInt(thisRarity);

        if(randRarity == 0)
        {
            ItemEntity entityitem = new ItemEntity((ServerWorld) thisEvent.getWorld(), thisPos.getX() + d0, thisPos.getY() + d1, thisPos.getZ() + d2, thisStack);
            thisEvent.getWorld().addEntity(entityitem);
        }
    }
    // ---------------------------------------------------------------------------------------------------------------------------
}
