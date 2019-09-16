package com.fizix.birdsneststres.setup;



import com.fizix.birdsneststres.BirdsNestsTres;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ItemManager extends Item {

    public ItemManager(String name)
    {
        super(new Properties().group(ItemGroup.MISC));
        this.setRegistryName(new ResourceLocation(BirdsNestsTres.MODID, name));
    }

}