package dev.mayaqq.estrogen.datagen.translations;

import dev.mayaqq.estrogen.registry.common.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EnUs extends FabricLanguageProvider {

    public EnUs(FabricDataGenerator output) {
        super(output, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder tb) {
        // Status Effects
        tb.add(EstrogenEffects.ESTROGEN_EFFECT, "Girl Power");

        // Controls
        tb.add("category.estrogen", "Estrogen");
        tb.add("key.estrogen.dash", "Activate Dash");

        // Items
        tb.add("itemGroup.estrogen.estrogen", "Estrogen");
        tb.add(EstrogenItems.ESTROGEN_PILL.get(), "Estrogen Pill");
        tb.add(EstrogenItems.ESTROGEN_PATCHES.get(), "Estrogen Patch");
        tb.add("item.estrogen.estrogen_patches_plural", "Estrogen Patches");
        tb.add(EstrogenItems.INCOMPLETE_ESTROGEN_PATCH.get(), "Incomplete Estrogen Patch");
        tb.add(EstrogenItems.CRYSTAL_ESTROGEN_PILL.get(), "Crystal Estrogen Pill");
        tb.add(EstrogenItems.ESTROGEN_CHIP_COOKIE.get(), "Estrogen Chip Cookie");
        tb.add("item.estrogen.estrogen_chip_cookie.desc", "erora - G03C");
        tb.add(EstrogenItems.BALLS.get(), "Balls");
        tb.add(EstrogenItems.HORSE_URINE_BOTTLE.get(), "Horse Urine Bottle");
        tb.add(EstrogenItems.TESTOSTERONE_CHUNK.get(), "Testosterone Chunk");
        tb.add(EstrogenItems.TESTOSTERONE_POWDER.get(), "Testosterone Powder");
        tb.add(EstrogenItems.USED_FILTER.get(), "Used Filter");
        tb.add(EstrogenItems.UWU.get(), ":3");
        tb.add("item.estrogen.uwu.tooltip", "§r§dUwU");
        tb.add(EstrogenItems.INCOMPLETE_UWU.get(), "Incomplete UwU");
        // Buckets
        tb.add(EstrogenFluidItems.LIQUID_ESTROGEN_BUCKET.get(), "Bucket of Liquid Estrogen");
        tb.add(EstrogenFluidItems.HORSE_URINE_BUCKET.get(), "Bucket of Horse Urine");
        tb.add(EstrogenFluidItems.FILTRATED_HORSE_URINE_BUCKET.get(), "Bucket of Filtrated Horse Urine");
        tb.add(EstrogenFluidItems.MOLTEN_SLIME_BUCKET.get(), "Bucket of Molten Slime");
        tb.add(EstrogenFluidItems.MOLTEN_AMETHYST_BUCKET.get(), "Bucket of Molten Amethyst");
        tb.add(EstrogenFluidItems.TESTOSTERONE_MIXTURE_BUCKET.get(), "Bucket of Testosterone Mixture");

        // Blocks
        tb.add(EstrogenBlocks.CENTRIFUGE.get(), "Centrifuge");
        // Fluids
        tb.add("fluid.estrogen.liquid_estrogen", "Liquid Estrogen");
        tb.add("fluid.estrogen.horse_urine", "Horse Urine");
        tb.add("fluid.estrogen.filtrated_horse_urine", "Filtrated Horse Urine");
        tb.add("fluid.estrogen.molten_slime", "Molten Slime");
        tb.add("fluid.estrogen.molten_amethyst", "Molten Amethyst");
        tb.add("fluid.estrogen.testosterone_mixture", "Testosterone Mixture");

        // Sounds
        tb.add("subtitles.estrogen.dash", "Girl Power Dash");

        // Death
        tb.add("death.attack.girlpower", "%s Girlbossed too hard");
        tb.add("death.attack.girlpower.player", "%s Girlbossed too hard");

        // Trinkets
        tb.add("trinkets.slot.legs.thighs", "Thighs");
        // Curios
        tb.add("curios.identifier.thighs", "Thighs");

        // REI
        tb.add("create.recipe.centrifuging", "Centrifuging");

        // Enchantments
        tb.add(EstrogenEnchantments.UWUFYING_CURSE.get(), "Curse of Uwufying");
        tb.add("enchantment.estrogen.uwufy_curse.desc", "UwUfies your chat messages >///<");

        // EMI
        tb.add("emi.category.estrogen.centrifuging", "Centrifuging");

        // Tags
        // Items
        tb.add("tag.item.trinkets.legs.thighs", "Thighs");
        tb.add("tag.item.estrogen.uwufying", "Uwufying");
        tb.add("tag.item.curios.thighs", "Thighs");
        tb.add("tag.item.estrogen.copper_plates", "Copper Plates");

        // Ponder
        // Centrifuge
        tb.add("estrogen.ponder.intro.header", "The Centrifuge Requirements");
        tb.add("estrogen.ponder.intro.text_1", "The centrifuge needs the maximum speed (256 RPM) to work!");
        tb.add("estrogen.ponder.basic.header", "How to use the Centrifuge");
        tb.add("estrogen.ponder.basic.text_1", "The Centrifuge doesn't have any inventory, you will need to place fluid containers around it to make it work!");
        tb.add("estrogen.ponder.basic.text_2", "You can input fluids from the bottom");
        tb.add("estrogen.ponder.basic.text_3", "And output fluids from the top");

        // Attributes
        tb.add("attribute.name.estrogen.dash_level", "Dash Level");
        tb.add("attribute.name.estrogen.boob_growing_start_time", "Upper body Start Time");
        tb.add("attribute.name.estrogen.boob_initial_size", "Upper Body initial size");
    }
}
