package com.fizix.birdsneststres.setup;

import com.fizix.birdsneststres.items.NestItem;
import com.fizix.birdsneststres.items.BanditPouchItem;
import com.fizix.birdsneststres.items.AncientTreasureItem;
import com.fizix.birdsneststres.items.SunkenTreasureItem;
import net.minecraft.item.Item;

public class ItemListManager
{
    public static Item BIRDSNEST = new NestItem("birdsnest");
    public static Item BANDITPOUCH = new BanditPouchItem("banditpouch");
    public static Item ANCIENTTREASURE = new AncientTreasureItem("ancienttreasure");
    public static Item SUNKENTREASURE = new SunkenTreasureItem("sunkentreasure");
}
