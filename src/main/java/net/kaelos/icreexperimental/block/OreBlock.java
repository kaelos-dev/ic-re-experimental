package net.kaelos.icreexperimental.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OreBlock extends ICREBlockBase {
    private final IntProvider xpRange;

    public OreBlock(boolean isDeepslate, IntProvider xpRange) {
        super(isDeepslate ? deepstateOreProp() : oreProp());
        this.xpRange = xpRange;
    }

    @Override
    protected void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
    }

    @Override
    public int getExpDrop(@NotNull BlockState state, @NotNull LevelAccessor level, @NotNull BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, @NotNull ItemStack tool) {
        return this.xpRange.sample(level.getRandom());
    }
}
