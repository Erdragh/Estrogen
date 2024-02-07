package dev.mayaqq.estrogen.platformSpecific.fabric;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.mayaqq.estrogen.config.EstrogenConfig;
import dev.mayaqq.estrogen.fabric.items.EstrogenPatchesItem;
import dev.mayaqq.estrogen.registry.common.EstrogenItems;
import net.minecraft.world.item.Item;

import static dev.mayaqq.estrogen.Estrogen.REGISTRATE;

public class PlatformSpecificRegistryImpl {
    @org.jetbrains.annotations.Contract
    public static ItemEntry<? extends Item> getRegisteredPatchesItem() {
        return REGISTRATE.item("estrogen_patches", EstrogenPatchesItem::new).properties(p -> new EstrogenItems.EstrogenProperties().stacksTo(EstrogenConfig.common().estrogenPatchesStackSize.get())).register();
    }
}