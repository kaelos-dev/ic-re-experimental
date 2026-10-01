package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.block.CableBlock;
import net.kaelos.icreexperimental.block.OreBlock;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ICREBlockTagProvider extends BlockTagsProvider {
    public ICREBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper exFileHelper) {
        super(output, lookupProvider, ICRE.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        var pickaxe = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
        var stoneTool = this.tag(BlockTags.NEEDS_STONE_TOOL);

        ICREBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(block -> block instanceof CableBlock)
                .forEach(block -> {
                    pickaxe.add(block);
                    stoneTool.add(block);
                });

        ICREBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(block -> block instanceof OreBlock)
                .forEach(pickaxe::add);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ICREBlocks.TIN_ORE.get())
                .add(ICREBlocks.DEEPSLATE_TIN_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ICREBlocks.DEEPSLATE_LEAD_ORE.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ICREBlocks.RUBBER_LOG.get());
    }
}
