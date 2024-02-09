package dev.mayaqq.estrogen.datagen.translations;

import dev.mayaqq.estrogen.registry.common.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class FrFr extends FabricLanguageProvider {
    public FrFr(FabricDataGenerator output) {
        super(output, "fr_fr");
    }

    @Override
    public void generateTranslations(TranslationBuilder tb) {
        // Status Effects
        tb.add(EstrogenEffects.ESTROGEN_EFFECT, "Force Féminine");

        // Controls
        tb.add("category.estrogen", "Œstrogène");
        tb.add("key.estrogen.dash", "Propulsion");

        // Items
        tb.add("itemGroup.estrogen", "Œstrogène");
        tb.add(EstrogenItems.ESTROGEN_PILL.get(), "Pilule d'Œstrogène");
        tb.add(EstrogenItems.ESTROGEN_PATCHES.get(), "Patch d'Œstrogène");
        tb.add("item.estrogen.estrogen_patches_plural", "Patchs d'Œstrogène");
        tb.add(EstrogenItems.INCOMPLETE_ESTROGEN_PATCH.get(), "Patch d'Œstrogène Incomplet");
        tb.add(EstrogenItems.CRYSTAL_ESTROGEN_PILL.get(), "Pilule de cristal d'Œstrogène");
        tb.add(EstrogenItems.ESTROGEN_CHIP_COOKIE.get(), "Cookie aux pépites d'Œstrogène");
        //tb.add("item.estrogen.estrogen_chip_cookie.desc", "erora - G03C");
        tb.add(EstrogenItems.BALLS.get(), "Boules");
        tb.add(EstrogenItems.HORSE_URINE_BOTTLE.get(), "Fiole d'urine de cheval");
        tb.add(EstrogenItems.TESTOSTERONE_CHUNK.get(), "Bloc de testostérone");
        tb.add(EstrogenItems.TESTOSTERONE_POWDER.get(), "Poudre de testostérone");
        tb.add(EstrogenItems.USED_FILTER.get(), "Filtre Usagé");
        //tb.add(EstrogenItems.UWU.get(), ":3");
        //tb.add("item.estrogen.uwu.tooltip", "§r§dUwU");
        tb.add(EstrogenItems.INCOMPLETE_UWU.get(), "UwU incomplet");
        // Buckets
        tb.add(EstrogenFluidItems.LIQUID_ESTROGEN_BUCKET.get(), "Seau d'Œstrogène liquide");
        tb.add(EstrogenFluidItems.HORSE_URINE_BUCKET.get(), "Seau d'rine de cheval");
        tb.add(EstrogenFluidItems.FILTRATED_HORSE_URINE_BUCKET.get(), "Seau d'urine de cheval");
        tb.add(EstrogenFluidItems.MOLTEN_SLIME_BUCKET.get(), "Seau de Slime fondu");
        tb.add(EstrogenFluidItems.MOLTEN_AMETHYST_BUCKET.get(), "Seau d'améthyste fondue");
        tb.add(EstrogenFluidItems.TESTOSTERONE_MIXTURE_BUCKET.get(), "Seau de mixture de testostérone");

        // Blocks
        tb.add(EstrogenBlocks.CENTRIFUGE.get(), "Centrifugeuse");
        // Fluids
        tb.add("fluid.estrogen.liquid_estrogen", "Œstrogène liquide");
        tb.add("fluid.estrogen.horse_urine", "Urine de cheval");
        tb.add("fluid.estrogen.filtrated_horse_urine", "Urine de cheval filtrée");
        tb.add("fluid.estrogen.molten_slime", "Slime fondu");
        tb.add("fluid.estrogen.molten_amethyst", "Améthyste fondue");
        tb.add("fluid.estrogen.testosterone_mixture", "Mixture de testostérone");

        // Sounds
        tb.add("subtitles.estrogen.dash", "Propulsion de forcé féminine");

        // Death
        tb.add("death.attack.girlpower", "%s a trop joué à la patronne");
        tb.add("death.attack.girlpower.player", "%s a trop joué à la patronne");

        // Trinkets
        tb.add("trinkets.slot.legs.thighs", "Cuisse");
        // Curios
        tb.add("curios.identifier.thighs", "Cuisse");

        // REI
        tb.add("create.recipe.centrifuging", "Centrifugeage");

        // Enchantments
        tb.add(EstrogenEnchantments.UWUFYING_CURSE.get(), "Malédiction d'uwufication");
        tb.add("enchantment.estrogen.uwufy_curse.desc", "UwUifie vos messages dans le chat >///<");

        // EMI
        tb.add("emi.category.estrogen.centrifuging", "Centrifugeage");

        // Tags
        // Items
        tb.add("tag.item.trinkets.legs.thighs", "Cuisses");
        tb.add("tag.item.estrogen.uwufying", "Uwufication");
        tb.add("tag.item.curios.thighs", "Cuisses");
        tb.add("tag.item.estrogen.copper_plates", "Plaques de cuivre");

        // Ponder
        // Centrifuge
        tb.add("estrogen.ponder.intro.header", "Besoins de la centrifugeuse");
        tb.add("estrogen.ponder.intro.text_1", "La centrifugeuse requiert la vitesse de rotation maximum pour fonctionner (par défaut 256 RPM).");
        tb.add("estrogen.ponder.basic.header", "Comment utiliser la centrifugeuse");
        tb.add("estrogen.ponder.basic.text_1", "La centrifuge ne dispose pas d'un inventaire, vous devez lui fournir l'espace de stockage avec des réservoirs à fluide au dessus et en dessous pour la faire fonctionner.");
        tb.add("estrogen.ponder.basic.text_2", "L'entrée de fluide se fait par le dessous");
        tb.add("estrogen.ponder.basic.text_3", "Et la sortie se fait par le dessus");

        // Attributes
        tb.add("attribute.name.estrogen.dash_level", "Niveau de propulsion");
        tb.add("attribute.name.estrogen.boob_growing_start_time", "Instant de début de poitrine");
        tb.add("attribute.name.estrogen.boob_initial_size", "Taille initiale de poitrine");
    }
}
