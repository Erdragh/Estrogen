package dev.mayaqq.estrogen.integrations.emi;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.compat.emi.recipes.CreateEmiRecipe;
import com.simibubi.create.compat.rei.ToolboxColoringRecipeMaker;
import com.simibubi.create.content.equipment.toolbox.ToolboxBlock;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import com.simibubi.create.foundation.item.TagDependentIngredientItem;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.mayaqq.estrogen.Estrogen;
import dev.mayaqq.estrogen.integrations.emi.recipes.CentrifugingEmiRecipe;
import dev.mayaqq.estrogen.registry.common.EstrogenBlocks;
import dev.mayaqq.estrogen.registry.common.EstrogenRecipes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EmiCompat implements EmiPlugin {
    public static final Map<Identifier, EmiRecipeCategory> ALL = new LinkedHashMap<>();

    public static final EmiRecipeCategory
            CENTRIFUGING = register("centrifuging", EmiStack.of(EstrogenBlocks.CENTRIFUGE.get()));

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void register(EmiRegistry registry) {
        registry.removeEmiStacks(s -> {
            Object key = s.getKey();
            if (key instanceof TagDependentIngredientItem tagDependent && tagDependent.shouldHide())
                return true;
            return key instanceof VirtualFluid;
        });

        registry.addGenericExclusionArea((screen, consumer) -> {
            if (screen instanceof AbstractSimiContainerScreen<?> simi) {
                simi.getExtraAreas().forEach(r -> consumer.accept(new Bounds(r.getX(), r.getY(), r.getWidth(), r.getHeight())));
            }
        });

        registerGeneratedRecipes(registry);

        ALL.forEach((id, category) -> registry.addCategory(category));

        registry.addWorkstation(CENTRIFUGING, EmiStack.of(EstrogenBlocks.CENTRIFUGE.get()));

        addAll(registry, EstrogenRecipes.CENTRIFUGING, CentrifugingEmiRecipe::new);
    }

    @SuppressWarnings("unchecked")
    private <T extends Recipe<?>> void addAll(EmiRegistry registry, EstrogenRecipes type, Function<T, EmiRecipe> constructor) {
        for (T recipe : (List<T>) registry.getRecipeManager().listAllOfType(type.getType())) {
            registry.addRecipe(constructor.apply(recipe));
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Recipe<?>> void addAll(EmiRegistry registry, AllRecipeTypes type, EmiRecipeCategory category, BiFunction<EmiRecipeCategory, T, EmiRecipe> constructor) {
        for (T recipe : (List<T>) registry.getRecipeManager().listAllOfType(type.getType())) {
            registry.addRecipe(constructor.apply(category, recipe));
        }
    }

    public void registerGeneratedRecipes(EmiRegistry registry) {
        ToolboxColoringRecipeMaker.createRecipes().forEach(r -> {
            ItemStack toolbox = null;
            ItemStack dye = null;
            for (Ingredient ingredient : r.getIngredients()) {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    if (toolbox == null && stack.getItem() instanceof BlockItem block && block.getBlock() instanceof ToolboxBlock) {
                        toolbox = stack;
                    } else if (dye == null && stack.getItem() instanceof DyeItem) {
                        dye = stack;
                    }
                    if (toolbox != null && dye != null) break;
                }
            }
            if (toolbox == null || dye == null) return;
            Identifier toolboxId = Registries.ITEM.getId(toolbox.getItem());
            Identifier dyeId = Registries.ITEM.getId(dye.getItem());
            String recipeName = "create/toolboxes/%s/%s/%s/%s"
                    .formatted(toolboxId.getNamespace(), toolboxId.getPath(), dyeId.getNamespace(), dyeId.getPath());
            registry.addRecipe(new EmiCraftingRecipe(
                    r.getIngredients().stream().map(EmiIngredient::of).toList(),
                    CreateEmiRecipe.getResultEmi(r), new Identifier("emi", recipeName)));
        });
        // for EMI we don't do this since it already has a category, World Interaction
//		LogStrippingFakeRecipes.createRecipes().forEach(r -> {
//			registry.addRecipe(new ItemApplicationEmiRecipe(r));
//		});
    }

    public static boolean doInputsMatch(Recipe<?> a, Recipe<?> b) {
        if (!a.getIngredients().isEmpty() && !b.getIngredients().isEmpty()) {
            ItemStack[] matchingStacks = a.getIngredients().get(0).getMatchingStacks();
            if (matchingStacks.length != 0) {
                if (b.getIngredients().get(0).test(matchingStacks[0])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static EmiRecipeCategory register(String name, EmiRenderable icon) {
        Identifier id = Estrogen.id(name);
        EmiRecipeCategory category = new EmiRecipeCategory(id, icon);
        ALL.put(id, category);
        return category;
    }
}
