package com.fizix.birdsneststres;


import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber
public class Config
{
    private static final Logger LOGGER = LogManager.getLogger();


    // Builder
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;



    // Categories
    public static final String CATEGORY_MAIN = "main";
    public static final String SUBCATEGORY_NESTS = "nests";
    public static final String SUBCATEGORY_BANDIT_POUCHES = "bandit pouches";
    public static final String SUBCATEGORY_ANCIENT_TREASURE = "ancient treasure";
    public static final String SUBCATEGORY_SUNKEN_TREASURE = "sunken treasure";

    // Config setting
    public static ForgeConfigSpec.BooleanValue NEST_ENABLED;
    public static ForgeConfigSpec.BooleanValue NEST_ALLOW_STACKING;
    public static ForgeConfigSpec.BooleanValue NEST_ALLOW_DECAY_DROPS;
    public static ForgeConfigSpec.IntValue NEST_DROP_RARITY_NORMAL;
    public static ForgeConfigSpec.IntValue NEST_DROP_RARITY_HIGH;
    public static ForgeConfigSpec.IntValue NEST_DROP_RARITY_LOW;


    public static ForgeConfigSpec.BooleanValue BANDIT_POUCH_ENABLED;
    public static ForgeConfigSpec.BooleanValue BANDIT_POUCH_ALLOW_STACKING;
    public static ForgeConfigSpec.IntValue BANDIT_POUCH_DROP_RARITY_NORMAL;
    public static ForgeConfigSpec.IntValue BANDIT_POUCH_DROP_RARITY_HIGH;
    public static ForgeConfigSpec.IntValue BANDIT_POUCH_DROP_RARITY_LOW;

    public static ForgeConfigSpec.BooleanValue ANCIENT_TREASURE_ENABLED;
    public static ForgeConfigSpec.BooleanValue ANCIENT_TREASURE_ALLOW_STACKING;
    public static ForgeConfigSpec.IntValue ANCIENT_TREASURE_DROP_RARITY_NORMAL;
    public static ForgeConfigSpec.IntValue ANCIENT_TREASURE_DROP_RARITY_HIGH;
    public static ForgeConfigSpec.IntValue ANCIENT_TREASURE_DROP_RARITY_LOW;

    public static ForgeConfigSpec.BooleanValue SUNKEN_TREASURE_ENABLED;
    public static ForgeConfigSpec.BooleanValue SUNKEN_TREASURE_ALLOW_STACKING;
    public static ForgeConfigSpec.IntValue SUNKEN_TREASURE_DROP_RARITY_NORMAL;
    public static ForgeConfigSpec.IntValue SUNKEN_TREASURE_DROP_RARITY_HIGH;
    public static ForgeConfigSpec.IntValue SUNKEN_TREASURE_DROP_RARITY_LOW;



    // Set the settings
    static {

        COMMON_BUILDER.comment("Bird's Nest & Treasures Settings").push(CATEGORY_MAIN);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Bird's Nest Settings").push(SUBCATEGORY_NESTS);
        NEST_ENABLED = COMMON_BUILDER.comment("Nests Enabled").define("nestEnabled", true);
        NEST_ALLOW_STACKING = COMMON_BUILDER.comment("Allow Stacking").define("nestAllowStacking", false);
        NEST_DROP_RARITY_NORMAL = COMMON_BUILDER.comment("Standard Drop Rate").defineInRange("nestRarityNormal", 50, 0, 1000);
        NEST_DROP_RARITY_LOW = COMMON_BUILDER.comment("Rare Drop Rate").defineInRange("nestRarityLow", 55, 0, 1000);
        NEST_DROP_RARITY_HIGH = COMMON_BUILDER.comment("Common Drop Rate").defineInRange("nestRarityHigh", 45, 0, 1000);
        NEST_ALLOW_DECAY_DROPS = COMMON_BUILDER.comment("Allow Decay Drops").define("nestAllowDecayDrops", true);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Bandit Treasure Pouch Settings").push(SUBCATEGORY_BANDIT_POUCHES);
        BANDIT_POUCH_ENABLED = COMMON_BUILDER.comment("Bandit Pouches Enabled").define("banditPouchEnabled", true);
        BANDIT_POUCH_ALLOW_STACKING = COMMON_BUILDER.comment("Allow Stacking").define("banditPouchAllowStacking", false);
        BANDIT_POUCH_DROP_RARITY_NORMAL = COMMON_BUILDER.comment("Standard Drop Rate").defineInRange("banditPouchRarityNormal", 50, 0, 1000);
        BANDIT_POUCH_DROP_RARITY_LOW = COMMON_BUILDER.comment("Rare Drop Rate").defineInRange("banditPouchRarityLow", 60, 0, 1000);
        BANDIT_POUCH_DROP_RARITY_HIGH = COMMON_BUILDER.comment("Common Drop Rate").defineInRange("banditPouchRarityHigh", 45, 0, 1000);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("Ancient Treasure Settings").push(SUBCATEGORY_ANCIENT_TREASURE);
        ANCIENT_TREASURE_ENABLED = COMMON_BUILDER.comment("Ancient Treasure Enabled").define("ancientTreasureEnabled", true);
        ANCIENT_TREASURE_ALLOW_STACKING = COMMON_BUILDER.comment("Allow Stacking").define("ancientTreasureAllowStacking", false);
        ANCIENT_TREASURE_DROP_RARITY_NORMAL = COMMON_BUILDER.comment("Standard Drop Rate").defineInRange("ancientTreasureRarityNormal", 55, 0, 1000);
        ANCIENT_TREASURE_DROP_RARITY_LOW = COMMON_BUILDER.comment("Rare Drop Rate").defineInRange("ancientTreasureRarityLow", 60, 0, 1000);
        ANCIENT_TREASURE_DROP_RARITY_HIGH = COMMON_BUILDER.comment("Common Drop Rate").defineInRange("ancientTreasureRarityHigh", 50, 0, 1000);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("Sunken Treasure Settings").push(SUBCATEGORY_SUNKEN_TREASURE);
        SUNKEN_TREASURE_ENABLED = COMMON_BUILDER.comment("Sunken Treasure Enabled").define("sunkenTreasureEnabled", true);
        SUNKEN_TREASURE_ALLOW_STACKING = COMMON_BUILDER.comment("Allow Stacking").define("sunkenTreasureAllowStacking", false);
        SUNKEN_TREASURE_DROP_RARITY_NORMAL = COMMON_BUILDER.comment("Standard Drop Rate").defineInRange("sunkenTreasureRarityNormal", 50, 0, 1000);
        SUNKEN_TREASURE_DROP_RARITY_LOW = COMMON_BUILDER.comment("Rare Drop Rate").defineInRange("sunkenTreasureRarityLow", 60, 0, 1000);
        SUNKEN_TREASURE_DROP_RARITY_HIGH = COMMON_BUILDER.comment("Common Drop Rate").defineInRange("sunkenTreasureRarityHigh", 45, 0, 1000);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }



    public static void loadConfig(ForgeConfigSpec spec, Path path)
    {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent)
    {
        LOGGER.debug("Config Loaded Event ");
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) { LOGGER.debug("Config Re-Loaded Event "); }

}
