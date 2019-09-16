package com.fizix.birdsneststres;

import com.fizix.birdsneststres.setup.ItemListManager;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;



@ObjectHolder(BirdsNestsTres.MODID)
@EventBusSubscriber(modid = BirdsNestsTres.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModRegistry
{

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(ItemListManager.BIRDSNEST);
        event.getRegistry().register(ItemListManager.BANDITPOUCH);
        event.getRegistry().register(ItemListManager.ANCIENTTREASURE);
        event.getRegistry().register(ItemListManager.SUNKENTREASURE);
    }

}
