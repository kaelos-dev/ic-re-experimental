package net.kaelos.icreexperimental.levelgen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kaelos.icreexperimental.definitions.ICREPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class RubberFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<RubberFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(inst ->
            foliagePlacerParts(inst).apply(inst, RubberFoliagePlacer::new));

    public RubberFoliagePlacer() {
        super(ConstantInt.of(0), ConstantInt.of(0));
    }

    public RubberFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ICREPlacerTypes.RUBBER_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader level, @NotNull FoliageSetter foliageSetter, @NotNull RandomSource random, @NotNull TreeConfiguration configuration, int maxFreeTreeHeight, @NotNull FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos pos = attachment.pos();
        if (TreeFeature.validTreePos(level, pos)) {
            foliageSetter.set(pos, configuration.foliageProvider.getState(random, pos));
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int height, @NotNull TreeConfiguration configuration) {
        return 4 + random.nextInt(5);
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
