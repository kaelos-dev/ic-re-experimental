package net.kaelos.icreexperimental.worldgen;

import net.kaelos.icreexperimental.ICRE;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ICREBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_RUBBER_SWAMP = registerKey("add_tree_rubber_swamp");
    public static final ResourceKey<BiomeModifier> ADD_TREE_RUBBER_JUNGLE = registerKey("add_tree_rubber_jungle");
    public static final ResourceKey<BiomeModifier> ADD_TREE_RUBBER_FOREST = registerKey("add_tree_rubber_forest");

    public static final ResourceKey<BiomeModifier> ADD_ORES_KEY = registerKey("add_ores");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Rubber Tree Swamp
        context.register(ADD_TREE_RUBBER_SWAMP, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_SWAMP),
                HolderSet.direct(placedFeatures.getOrThrow(ICREPlacedFeatures.RUBBER_SWAMP_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // Rubber Tree Jungle
        context.register(ADD_TREE_RUBBER_JUNGLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                HolderSet.direct(placedFeatures.getOrThrow(ICREPlacedFeatures.RUBBER_JUNGLE_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // Rubber Tree Forest
        context.register(ADD_TREE_RUBBER_FOREST, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ICREPlacedFeatures.RUBBER_FOREST_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // Ores
        context.register(ADD_ORES_KEY, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(
                        placedFeatures.getOrThrow(ICREPlacedFeatures.TIN_ORE_PLACED_KEY),
                        placedFeatures.getOrThrow(ICREPlacedFeatures.LEAD_ORE_PLACED_KEY)
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ICRE.makeId(name));
    }
}
