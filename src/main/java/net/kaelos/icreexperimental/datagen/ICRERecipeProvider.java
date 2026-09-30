package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.kaelos.icreexperimental.definitions.ICREItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
        hammerRecipe(Items.GOLD_INGOT, ICREItems.GOLD_PLATE.get(), output);

        cutterRecipe(ICREItems.TIN_PLATE.get(), ICREBlocks.TIN_CABLE.get().asItem(), 3, output);
        cutterRecipe(ICREItems.COPPER_PLATE.get(), ICREBlocks.COPPER_CABLE.get().asItem(), 2, output);
        cutterRecipe(ICREItems.GOLD_PLATE.get(), ICREBlocks.GOLD_CABLE.get().asItem(), 4, output);

        smeltingRecipe(ICREItems.RESIN.get(), ICREItems.RUBBER.get(), 0.1F, output);
    }

    private void smeltingRecipe(Item input, Item output, float xp, RecipeOutput recipeOutput) {
        String nameInputItem = BuiltInRegistries.ITEM.getKey(input).getPath();
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(input),
                RecipeCategory.MISC,
                output,
                xp,
                200
        ).unlockedBy("has_" + nameInputItem, has(input)).save(recipeOutput);
    }

    private void hammerRecipe(Item input, Item output, RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output)
                .requires(ICREItems.HAMMER.get())
                .requires(input)
                .unlockedBy("has_hammer", has(ICREItems.HAMMER.get()))
                .save(recipeOutput);
    }

    private void cutterRecipe(Item input, Item output, int count, RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, count)
                .requires(ICREItems.CUTTER.get())
                .requires(input)
                .unlockedBy("has_cutting", has(ICREItems.CUTTER.get()))
                .save(recipeOutput);
    }
}
