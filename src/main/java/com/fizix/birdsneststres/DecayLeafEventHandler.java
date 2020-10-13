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

public class DecayLeafEventHandler
{
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onDrops(BlockEvent.BreakEvent event)
    {
        Block theblock = event.getState().getBlock();
        Random random = new Random();
        BlockPos pos = event.getPos();

        double d0 = random.nextFloat() * 0.5D +0.25D;
        double d1 = random.nextFloat() * 0.5D +0.25D;
        double d2 = random.nextFloat() * 0.5D +0.25D;

        // Bird Nest Drop
        if(theblock.isIn(BlockTags.LEAVES) && Config.NEST_ALLOW_DECAY_DROPS.get() == true && Config.NEST_ENABLED.get() == true)
        {
            if(random.nextInt(Config.NEST_DROP_RARITY_LOW.get()) == 0)
            {
                ItemStack stack = new ItemStack(ItemListManager.BIRDSNEST,1);
                ItemEntity entityitem = new ItemEntity((ServerWorld) event.getWorld(), pos.getX()+d0, pos.getY()+d1, pos.getZ()+d2, stack);
                event.getWorld().addEntity(entityitem);
            }
        }

    }
}
