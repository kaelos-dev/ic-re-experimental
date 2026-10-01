package net.kaelos.icreexperimental.worldgen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ICREPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RUBBER_SWAMP_KEY = registerKey("rubber_swamp");
    public static final ResourceKey<PlacedFeature> RUBBER_JUNGLE_KEY = registerKey("rubber_jungle");
    public static final ResourceKey<PlacedFeature> RUBBER_FOREST_KEY = registerKey("rubber_forest");

    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registerKey("lead_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> rubberHolder = configuredFeatures.getOrThrow(ICREConfiguredFeatures.RUBBER_KEY);

        // Swamp (general + mangrove): ~25%
        register(context, RUBBER_SWAMP_KEY, rubberHolder, List.of(
                RarityFilter.onAverageOnceEvery(4),
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(1),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ICREBlocks.RUBBER_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome()
        ));

        // Jungle: ~16%
        register(context, RUBBER_JUNGLE_KEY, rubberHolder, List.of(
                RarityFilter.onAverageOnceEvery(6),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ICREBlocks.RUBBER_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome()
        ));

        // Forest: ~6%
        register(context, RUBBER_FOREST_KEY, rubberHolder, List.of(
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ICREBlocks.RUBBER_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome()
        ));

        // Tin Ores
        register(context, TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ICREConfiguredFeatures.OVERWORLD_TIN_ORE_KEY),
                oreModifiers(
                        CountPlacement.of(14),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))
                )
        );

        // Lead Ores
        register(context, LEAD_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ICREConfiguredFeatures.OVERWORLD_LEAD_ORE_KEY),
                oreModifiers(
                        CountPlacement.of(6),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))
                )
        );
    }

    private static List<PlacementModifier> oreModifiers(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ICRE.makeId(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, modifiers));
    }
}
