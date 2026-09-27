package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.definitions.ICREItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ICRERecipeProvider extends RecipeProvider {
    public ICRERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        hammerRecipe(ICREItems.TIN_INGOT.get(), ICREItems.TIN_PLATE.get(), output);
        hammerRecipe(ICREItems.LEAD_INGOT.get(), ICREItems.LEAD_PLATE.get(), output);
        hammerRecipe(ICREItems.BRONZE_INGOT.get(), ICREItems.BRONZE_PLATE.get(), output);
        hammerRecipe(Items.IRON_INGOT, ICREItems.IRON_PLATE.get(), output);
        hammerRecipe(Items.COPPER_INGOT, ICREItems.COPPER_PLATE.get(), output);
    }

    private void hammerRecipe(Item input, Item output, RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output)
                .requires(ICREItems.HAMMER.get())
                .requires(input)
                .unlockedBy("has_hammer", has(ICREItems.HAMMER.get()))
                .save(recipeOutput);
    }
}
