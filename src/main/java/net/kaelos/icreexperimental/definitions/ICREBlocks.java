package net.kaelos.icreexperimental.definitions;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.block.CableBlock;
import net.kaelos.icreexperimental.block.rubber.RubberLeaves;
import net.kaelos.icreexperimental.block.rubber.RubberLog;
import net.kaelos.icreexperimental.block.rubber.RubberSapling;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ICREBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ICRE.MOD_ID);

    public static final DeferredBlock<CableBlock> TIN_CABLE = registerBlock("tin_cable", CableBlock::new);
    public static final DeferredBlock<CableBlock> COPPER_CABLE = registerBlock("copper_cable", CableBlock::new);
    public static final DeferredBlock<CableBlock> GOLD_CABLE = registerBlock("gold_cable", CableBlock::new);
    public static final DeferredBlock<CableBlock> IRON_CABLE = registerBlock("iron_cable", CableBlock::new);

    public static final DeferredBlock<RubberLog> RUBBER_LOG = registerBlock("rubber_log", RubberLog::new);
    public static final DeferredBlock<RubberLeaves> RUBBER_LEAVES = registerBlock("rubber_leaves", RubberLeaves::new);
    public static final DeferredBlock<RubberSapling> RUBBER_SAPLING = registerBlock("rubber_sapling", RubberSapling::new);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> object = BLOCKS.register(name, block);
        registerBlockItem(name, object);
        return object;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ICREItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
