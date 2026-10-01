package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.block.CableBlock;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.kaelos.icreexperimental.definitions.ICREItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ICREBlockLootProvider extends BlockLootSubProvider {
    protected ICREBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        ICREBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(block -> block instanceof CableBlock)
                .forEach(this::dropSelf);

        createRubber();
        createOres();
    }

    private void createOres() {
        add(ICREBlocks.TIN_ORE.get(), block -> createOreDrop(ICREBlocks.TIN_ORE.get(), ICREItems.RAW_TIN.get()));
        add(ICREBlocks.DEEPSLATE_TIN_ORE.get(), block -> createOreDrop(ICREBlocks.DEEPSLATE_TIN_ORE.get(), ICREItems.RAW_TIN.get()));
        add(ICREBlocks.DEEPSLATE_LEAD_ORE.get(), block -> createOreDrop(ICREBlocks.DEEPSLATE_LEAD_ORE.get(), ICREItems.RAW_LEAD.get()));
    }

    private void createRubber() {
        this.dropSelf(ICREBlocks.RUBBER_LOG.get());
        this.dropSelf(ICREBlocks.RUBBER_SAPLING.get());
        this.add(ICREBlocks.RUBBER_LEAVES.get(), block ->
                createLeavesDrops(block, ICREBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ICREBlocks.BLOCKS.getEntries().stream()
                .map(Holder::value)::iterator;
    }
}
