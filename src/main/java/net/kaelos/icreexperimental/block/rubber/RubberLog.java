package net.kaelos.icreexperimental.block.rubber;

import net.kaelos.icreexperimental.block.ICREBlockBase;
import net.kaelos.icreexperimental.definitions.ICREItems;
import net.kaelos.icreexperimental.definitions.ICRESounds;
import net.kaelos.icreexperimental.init.IAgricultureComponent;
import net.kaelos.icreexperimental.item.tool.FaucetTool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RubberLog extends ICREBlockBase implements IAgricultureComponent {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public static final DirectionProperty RESIN_FACING = DirectionProperty.create("resin_facing", Direction.Plane.HORIZONTAL);
    public static final BooleanProperty RESIN = BooleanProperty.create("resin");
    public static final BooleanProperty COLLECTABLE = BooleanProperty.create("collectable");

    public RubberLog() {
        super(Properties.ofFullCopy(Blocks.OAK_LOG));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(RESIN_FACING, Direction.NORTH)
                .setValue(RESIN, false)
                .setValue(COLLECTABLE, false));
    }

    @Override
    protected @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation) {
        return rotatePillar(state, rotation);
    }

    private static BlockState rotatePillar(BlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case X -> {
                        return state.setValue(AXIS, Direction.Axis.Z);
                    }
                    case Z -> {
                        return state.setValue(AXIS, Direction.Axis.X);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AXIS, RESIN_FACING, RESIN, COLLECTABLE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (stack.getItem() instanceof FaucetTool) {
            if (state.hasProperty(RESIN) && state.getValue(RESIN)) {
                boolean isCollectable = state.hasProperty(COLLECTABLE) && state.getValue(COLLECTABLE);

                BlockState newState;
                ItemStack dropStack;

                if (isCollectable) {
                    int count = level.getRandom().nextInt(1, 4);
                    dropStack = new ItemStack(ICREItems.RESIN.get(), count);
                    newState = state.setValue(COLLECTABLE, false);
                } else {
                    dropStack = new ItemStack(ICREItems.RESIN.get(), 1);
                    newState = state.setValue(RESIN, false);
                }

                if (!level.isClientSide()) {
                    level.setBlock(pos, newState, Block.UPDATE_ALL);
                    Block.popResource(level, pos, dropStack);
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                }

                level.playSound(player, pos, ICRESounds.FAUCET_EXTRACT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                return ItemInteractionResult.sidedSuccess(level.isClientSide());
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
