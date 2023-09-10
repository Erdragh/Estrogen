package dev.mayaqq.estrogen.registry.common;

import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.Lang;
import dev.mayaqq.estrogen.registry.common.recipes.CentrifugingRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

import static dev.mayaqq.estrogen.Estrogen.id;

public enum EstrogenRecipes implements IRecipeTypeInfo {
    CENTRIFUGING(CentrifugingRecipe::new);

    private final Identifier id;
    private final RecipeSerializer<?> serializerObject;
    @Nullable
    private final RecipeType<?> typeObject;
    private final Supplier<RecipeType<?>> type;

    EstrogenRecipes(Supplier<RecipeSerializer<?>> serializerSupplier, Supplier<RecipeType<?>> typeSupplier, boolean registerType) {
        String name = Lang.asId(name());
        id = Create.asResource(name);
        serializerObject = Registry.register(Registries.RECIPE_SERIALIZER, id, serializerSupplier.get());
        if (registerType) {
            typeObject = typeSupplier.get();
            Registry.register(Registries.RECIPE_TYPE, id, typeObject);
            type = typeSupplier;
        } else {
            typeObject = null;
            type = typeSupplier;
        }
    }

    EstrogenRecipes(Supplier<RecipeSerializer<?>> serializerSupplier) {
        String name = Lang.asId(name());
        id = id(name);
        serializerObject = Registry.register(Registries.RECIPE_SERIALIZER, id, serializerSupplier.get());
        typeObject = simpleType(id);
        Registry.register(Registries.RECIPE_TYPE, id, typeObject);
        type = () -> typeObject;
    }

    EstrogenRecipes(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> processingFactory) {
        this(() -> new ProcessingRecipeSerializer<>(processingFactory));
    }

    public static <T extends Recipe<?>> RecipeType<T> simpleType(Identifier id) {
        String stringId = id.toString();
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return stringId;
            }
        };
    }

    public static void register() {}

    @Override
    public Identifier getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializerObject;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeType<?>> T getType() {
        return (T) type.get();
    }

    public <C extends Inventory, T extends Recipe<C>> Optional<T> find(C inv, World world) {
        return world.getRecipeManager()
                .getFirstMatch(getType(), inv, world);
    }

    public static boolean shouldIgnoreInAutomation(Recipe<?> recipe) {
        RecipeSerializer<?> serializer = recipe.getSerializer();
        if (serializer != null)
            return true;
        return recipe.getId()
                .getPath()
                .endsWith("_manual_only");
    }
}
