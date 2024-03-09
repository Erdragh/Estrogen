package dev.mayaqq.estrogen.registry.common.items;


import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UwUItem extends Item {
    public UwUItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable net.minecraft.world.level.Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(new TranslatableComponent("item.estrogen.uwu.tooltip"));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
