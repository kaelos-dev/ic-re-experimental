package net.kaelos.icreexperimental.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class ICREBlockEntity<T extends BlockEntity> extends ICREBlockBase implements EntityBlock {
    private final BiFunction<BlockPos, BlockState, T> factory;

    public ICREBlockEntity(Properties properties, BiFunction<BlockPos, BlockState, T> factory) {
        super(properties);
        this.factory = factory;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return this.factory.apply(blockPos, blockState);
    }
}
