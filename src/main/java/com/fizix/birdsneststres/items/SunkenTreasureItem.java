package com.fizix.birdsneststres.items;


import com.fizix.birdsneststres.BirdsNestsTres;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class SunkenTreasureItem extends Item
{
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation("birdsneststres:sunkentreasure_loot");
    private static final Logger LOGGER = LogManager.getLogger();


    public SunkenTreasureItem(String name)
    {
        super(new Properties()
                .maxStackSize(BirdsNestsTres.sunkenTreasureStackSize)
                .group(ItemGroup.MISC)
        );
        this.setRegistryName(new ResourceLocation(BirdsNestsTres.MODID, name));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        // Ideally check whether we are in creative or survival
        itemstack.shrink(1);

        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            this.generateLoot(worldIn,playerIn);
        }

        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }





    private void generateLoot(World world,PlayerEntity player)
    {
        if (LOOT_TABLE == LootTables.EMPTY)
        {
        }
        else
        {
            LootTable loottable = ServerLifecycleHooks.getCurrentServer().getLootTableManager().getLootTableFromLocation(LOOT_TABLE);
            LootContext.Builder builder = new LootContext.Builder((ServerWorld) world);
            LootContext lootcontext = builder.withParameter(LootParameters.POSITION, player.getPosition()).withParameter(LootParameters.THIS_ENTITY, player).build(LootParameterSets.GIFT);

            List<ItemStack> itemstacklist = loottable.generate(lootcontext);

            for (ItemStack itemstack : itemstacklist)
            {
                ItemEntity entityitem = new ItemEntity(world, player.getPosX(), player.getPosY() + 1.5D, player.getPosZ(), itemstack);
                world.addEntity(entityitem);
            }
        }
    }
}