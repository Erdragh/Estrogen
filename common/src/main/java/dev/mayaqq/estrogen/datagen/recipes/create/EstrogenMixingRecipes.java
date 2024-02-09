package dev.mayaqq.estrogen.datagen.recipes.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeFabricImpl;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeForgeImpl;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeInterface;
import dev.mayaqq.estrogen.registry.common.EstrogenFluids;
import dev.mayaqq.estrogen.registry.common.EstrogenItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import static dev.mayaqq.estrogen.Estrogen.id;

public class EstrogenMixingRecipes<T extends EstrogenRecipeInterface> extends ProcessingRecipeGen {

    private T t;
    GeneratedRecipe
            FILATRATED_HORSE_URINE = create(id("filtrated_horse_urine"), recipeBuilder -> recipeBuilder
            .require(EstrogenFluids.HORSE_URINE.get(), t.getAmount(27000))
            .require(AllItems.FILTER.get())
            .output(EstrogenFluids.FILTRATED_HORSE_URINE.get(), t.getAmount(27000))
            .output(EstrogenItems.USED_FILTER.get())),
            MOLTEN_AMETHYST = create(id("molten_amethyst"), recipeBuilder -> recipeBuilder
                    .require(Items.AMETHYST_SHARD)
                    .output(EstrogenFluids.MOLTEN_AMETHYST.get(), t.getAmount(27000))
                    .requiresHeat(HeatCondition.HEATED)),
            BALLS = create(id("balls"), recipeBuilder -> recipeBuilder
                    .require(Items.SLIME_BALL)
                    .output(EstrogenItems.BALLS.get())
                    .output(EstrogenFluids.MOLTEN_SLIME.get(), t.getAmount(27000))
                    .requiresHeat(HeatCondition.HEATED)),
            TESTOSTERONE_MIXTURE = create(id("testosterone_mixture"), recipeBuilder -> recipeBuilder
                    .require(EstrogenItems.TESTOSTERONE_POWDER.get())
                    .require(Items.COAL)
                    .output(EstrogenFluids.TESTOSTERONE_MIXTURE.get(), t.getAmount(54000))
                    .requiresHeat(HeatCondition.HEATED));

    public EstrogenMixingRecipes(FabricDataGenerator output, T t) {
        super(output);
        this.t = t;
    }

    public static EstrogenMixingRecipes buildFabric(FabricDataGenerator output) {
        return new EstrogenMixingRecipes<>(output, new EstrogenRecipeFabricImpl());
    }

    public static EstrogenMixingRecipes buildForge(FabricDataGenerator output) {
        return new EstrogenMixingRecipes<>(output, new EstrogenRecipeForgeImpl());
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.MIXING;
    }

    @Override
    protected ResourceLocation getRecipeIdentifier(ResourceLocation identifier) {
        return t.getRecipeIdentifier(identifier);
    }

    @Override
    public String getName() {
        return t.getName(super.getName());
    }
}
