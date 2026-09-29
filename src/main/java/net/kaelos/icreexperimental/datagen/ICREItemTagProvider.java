package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ICREItemTagProvider extends ItemTagsProvider {
    public ICREItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper exFileHelper) {
        super(output, lookupProvider, blockTags, ICRE.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ICREBlocks.RUBBER_LOG.get().asItem());
    }
}
