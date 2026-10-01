package net.kaelos.icreexperimental.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

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

    public static Properties oreProp() {
        return Properties.of()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F);
    }

    public static Properties deepstateOreProp() {
        return Properties.of()
                .mapColor(MapColor.DEEPSLATE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .strength(4.5F, 3.0F)
                .sound(SoundType.DEEPSLATE);
    }
}
