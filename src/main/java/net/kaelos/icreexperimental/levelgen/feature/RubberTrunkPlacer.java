package net.kaelos.icreexperimental.levelgen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kaelos.icreexperimental.block.rubber.RubberLog;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.kaelos.icreexperimental.definitions.ICREPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RubberTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<RubberTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(inst ->
            trunkPlacerParts(inst).apply(inst, RubberTrunkPlacer::new));

    public RubberTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ICREPlacerTypes.RUBBER_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader level, @NotNull BiConsumer<BlockPos, BlockState> blockSetter, @NotNull RandomSource random, int freeTreeHeight, @NotNull BlockPos pos, @NotNull TreeConfiguration configuration) {
        setDirtAt(level, blockSetter, random, pos.below(), configuration);

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();
        int resinChange = 25;

        int guaranteedResinY = random.nextInt(freeTreeHeight);

        for (int y = 0; y < freeTreeHeight; y++) {
            BlockPos currentLogPos = pos.above(y);
            BlockState logState = ICREBlocks.RUBBER_LOG.get().defaultBlockState();

            boolean isGuaranteed = (y == guaranteedResinY);
            boolean  rolledRandom = (random.nextInt(100) <= resinChange);

            if (isGuaranteed || rolledRandom) {
                if (!isGuaranteed) {
                    resinChange -= 10;
                }

                Direction randomFacing = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                logState = logState.setValue(RubberLog.RESIN_FACING, randomFacing)
                        .setValue(RubberLog.RESIN, true)
                        .setValue(RubberLog.COLLECTABLE, true);
            }

            placeRubberLog(level, currentLogPos, blockSetter, logState);

            if (freeTreeHeight < 4 || (freeTreeHeight < 7 && y > 1) || y > 2) {
                int centerX = pos.getX();
                int centerZ = pos.getZ();

                for (int dx = -2; dx <= 2; dx++) {
                    for (int dz = -2; dz <= 2; dz++) {
                        int distFromTop = Math.max(1, (y + 4) - freeTreeHeight);

                        boolean isInner = Math.abs(dx) < 2 && Math.abs(dz) < 2;
                        boolean isEdgeX = Math.abs(dx) < 2 && random.nextInt(distFromTop) == 0;
                        boolean isEdgeZ = Math.abs(dz) < 2 && random.nextInt(distFromTop) == 0;

                        if (isInner || isEdgeX || isEdgeZ) {
                            BlockPos leavePos = new BlockPos(centerX + dx, pos.getY() + y, centerZ + dz);
                            if (TreeFeature.isAirOrLeaves(level, leavePos)) {
                                attachments.add(new FoliagePlacer.FoliageAttachment(leavePos, 0, true));
                            }
                        }
                    }
                }
            }
        }

        for (int dy = 0; dy <= 2; dy++) {
            BlockPos topLeavePos = pos.above(freeTreeHeight + dy);
            if (TreeFeature.isAirOrLeaves(level, topLeavePos)) {
                attachments.add(new FoliagePlacer.FoliageAttachment(topLeavePos, 0, true));
            }
        }

        return attachments;
    }

    private static void placeRubberLog(LevelSimulatedReader level, BlockPos pos, BiConsumer<BlockPos, BlockState> blockSetter, BlockState state) {
        if (TreeFeature.validTreePos(level, pos)) {
            blockSetter.accept(pos, state);
        }
    }
}
