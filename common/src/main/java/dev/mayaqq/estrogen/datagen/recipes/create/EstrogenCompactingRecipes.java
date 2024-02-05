package dev.mayaqq.estrogen.datagen.recipes.create;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeFabricImpl;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeForgeImpl;
import dev.mayaqq.estrogen.datagen.recipes.EstrogenRecipeInterface;
import dev.mayaqq.estrogen.registry.common.EstrogenFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import static dev.mayaqq.estrogen.Estrogen.id;

public class EstrogenCompactingRecipes<T extends EstrogenRecipeInterface> extends ProcessingRecipeGen {

    private T t;
    GeneratedRecipe SLIME_BALL = create(id("slime_ball"), recipeBuilder -> recipeBuilder
            .require(EstrogenFluids.MOLTEN_SLIME.get(), t.getAmount(54000))
            .output(Items.SLIME_BALL, 1));

    public EstrogenCompactingRecipes(FabricDataGenerator output, T t) {
        super(output);
        this.t = t;
    }

    public static EstrogenCompactingRecipes buildFabric(FabricDataGenerator output) {
        return new EstrogenCompactingRecipes<>(output, new EstrogenRecipeFabricImpl());
    }

    public static EstrogenCompactingRecipes buildForge(FabricDataGenerator output) {
        return new EstrogenCompactingRecipes<>(output, new EstrogenRecipeForgeImpl());
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.COMPACTING;
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
