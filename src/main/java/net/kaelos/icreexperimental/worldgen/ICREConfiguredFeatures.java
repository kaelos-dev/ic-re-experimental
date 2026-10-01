package net.kaelos.icreexperimental.worldgen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.kaelos.icreexperimental.levelgen.feature.RubberFoliagePlacer;
import net.kaelos.icreexperimental.levelgen.feature.RubberTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ICREConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_KEY = registerKey("rubber");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_KEY = registerKey("deepslate_lead_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, RUBBER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ICREBlocks.RUBBER_LOG.get()),
                new RubberTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(ICREBlocks.RUBBER_LEAVES.get()),
                new RubberFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> tinTargets = List.of(
                OreConfiguration.target(stoneReplaceables, ICREBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ICREBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
        );
        register(context, OVERWORLD_TIN_ORE_KEY, Feature.ORE, new OreConfiguration(tinTargets, 10, 0.0F));

        List<OreConfiguration.TargetBlockState> leadTargets = List.of(
                OreConfiguration.target(deepslateReplaceables, ICREBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())
        );
        register(context, OVERWORLD_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(leadTargets, 7, 0.0F));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ICRE.makeId(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
