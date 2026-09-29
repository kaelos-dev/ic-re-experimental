package net.kaelos.icreexperimental.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ICREBlockBase extends Block {
    public ICREBlockBase(Properties properties) {
        super(properties);
    }

    public static Properties metalProp() {
        return Properties.of()
                .strength(2.5F, 2.5F)
                .requiresCorrectToolForDrops()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .sound(SoundType.METAL);
    }
}
