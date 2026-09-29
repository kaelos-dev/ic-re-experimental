package net.kaelos.icreexperimental.worldgen.tree;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.worldgen.ICREConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ICRETreeGrowers {
    public static final TreeGrower RUBBER = new TreeGrower(ICRE.MOD_ID + ":rubber",
            Optional.empty(), Optional.of(ICREConfiguredFeatures.RUBBER_KEY), Optional.empty());
}
