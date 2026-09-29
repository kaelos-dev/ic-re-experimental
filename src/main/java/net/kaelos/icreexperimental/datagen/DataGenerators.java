package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = ICRE.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        BlockTagsProvider blockTags = new ICREBlockTagProvider(output, lookupProvider, exFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ICREItemTagProvider(output, lookupProvider, blockTags.contentsGetter(), exFileHelper));

        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ICREBlockLootProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        generator.addProvider(event.includeServer(), new ICRERecipeProvider(output, lookupProvider));

        generator.addProvider(event.includeClient(), new ICREItemModelProvider(output, exFileHelper));
        generator.addProvider(event.includeClient(), new ICREBlockStateProvider(output, exFileHelper));

        generator.addProvider(event.includeServer(), new ICREDatapackProvider(output, lookupProvider));
    }
}
