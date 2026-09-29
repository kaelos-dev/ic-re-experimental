package net.kaelos.icreexperimental.block.rubber;

import net.kaelos.icreexperimental.init.IAgricultureComponent;
import net.kaelos.icreexperimental.worldgen.tree.ICRETreeGrowers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public class RubberSapling extends SaplingBlock implements IAgricultureComponent {
    public RubberSapling() {
        super(ICRETreeGrowers.RUBBER, Properties.ofFullCopy(Blocks.OAK_SAPLING));
    }
}
