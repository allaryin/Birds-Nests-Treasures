package com.fizix.birdsneststres;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(BirdsNestsTres.MODID)

public class BirdsNestsTres
{
    public static final String MODID = "birdsneststres";

    private static final Logger LOGGER = LogManager.getLogger();

    // Settings
    public static boolean nestsEnabled = true;
    public static double nestRarityNormal = 0.20f;
    public static double nestRarityLow = 0.15f;
    public static double nestRarityHigh = 0.25f;
    public static boolean nestAllowStacking = false;
    public static int nestStackSize = 12;
    public static boolean allowDecayDrops = true;

    public static boolean banditPouchEnabled = true;
    public static double banditPouchRarityNormal = 0.20f;
    public static double banditPouchRarityLow = 0.15f;
    public static double banditPouchRarityHigh = 0.25f;
    public static boolean banditPouchAllowStacking = false;
    public static int banditPouchStackSize = 12;


    public static boolean ancientTreasureEnabled = true;
    public static double ancientTreasureRarityNormal = 0.20f;
    public static double ancientTreasureRarityLow = 0.15f;
    public static double ancientTreasureRarityHigh = 0.25f;
    public static boolean ancientTreasureAllowStacking = false;
    public static int ancientTreasureStackSize = 12;



    public static boolean sunkenTreasureEnabled = true;
    public static double sunkenTreasureRarityNormal = 0.20f;
    public static double sunkenTreasureRarityLow = 0.15f;
    public static double sunkenTreasureRarityHigh = 0.25f;
    public static boolean sunkenTreasureAllowStacking = false;
    public static int sunkenTreasureStackSize = 12;



    public BirdsNestsTres()
    {
        // Set config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("birdsneststres-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("birdsneststres-common.toml"));


        setSettings();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(new HarvestTreasureEventHandler());
        MinecraftForge.EVENT_BUS.register(new DecayLeafEventHandler());
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        // PreInit Code Here
    }


    private static void setSettings()
    {
        BirdsNestsTres.nestsEnabled = Config.NEST_ENABLED.get();
        BirdsNestsTres.nestAllowStacking = Config.NEST_ALLOW_STACKING.get();

        BirdsNestsTres.allowDecayDrops = Config.NEST_ALLOW_DECAY_DROPS.get();


        if(BirdsNestsTres.nestAllowStacking == false)
        {
            BirdsNestsTres.nestStackSize = 1;
        }
        else
        {
            BirdsNestsTres.nestStackSize = 12;
        }


        BirdsNestsTres.banditPouchEnabled = Config.BANDIT_POUCH_ENABLED.get();
        BirdsNestsTres.banditPouchAllowStacking = Config.BANDIT_POUCH_ALLOW_STACKING.get();


        if(BirdsNestsTres.banditPouchAllowStacking == false)
        {
            BirdsNestsTres.banditPouchStackSize = 1;
        }
        else
        {
            BirdsNestsTres.banditPouchStackSize = 12;
        }


        BirdsNestsTres.ancientTreasureEnabled = Config.ANCIENT_TREASURE_ENABLED.get();
        BirdsNestsTres.ancientTreasureAllowStacking = Config.ANCIENT_TREASURE_ALLOW_STACKING.get();


        if(BirdsNestsTres.ancientTreasureAllowStacking == false)
        {
            BirdsNestsTres.ancientTreasureStackSize = 1;
        }
        else
        {
            BirdsNestsTres.ancientTreasureStackSize = 12;
        }


        BirdsNestsTres.sunkenTreasureEnabled = Config.SUNKEN_TREASURE_ENABLED.get();
        BirdsNestsTres.sunkenTreasureAllowStacking = Config.SUNKEN_TREASURE_ALLOW_STACKING.get();


        if(BirdsNestsTres.sunkenTreasureAllowStacking == false)
        {
            BirdsNestsTres.sunkenTreasureStackSize = 1;
        }
        else
        {
            BirdsNestsTres.sunkenTreasureStackSize = 12;
        }
    }

}


