package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.block.CableBlock;
import net.kaelos.icreexperimental.block.rubber.RubberLog;
import net.kaelos.icreexperimental.definitions.ICREBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ICREBlockStateProvider extends BlockStateProvider {
    public ICREBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ICRE.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ICREBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(block -> block instanceof CableBlock)
                .forEach(this::cableBlocks);

        rubberBlocks();
    }

    private void rubberBlocks() {
        Block log = ICREBlocks.RUBBER_LOG.get();
        String nameLog = BuiltInRegistries.BLOCK.getKey(log).getPath();

        ResourceLocation side = modLoc("block/rubber/" + nameLog);
        ResourceLocation top = modLoc("block/rubber/" + nameLog + "_top");
        ResourceLocation resinFull = modLoc("block/rubber/" + nameLog + "_resin_full");
        ResourceLocation resinEmpty = modLoc("block/rubber/" + nameLog + "_resin_empty");

        ModelFile verticalLog = models().cubeColumn(nameLog, side, top);
        ModelFile horizontalLog = models().cubeColumnHorizontal(nameLog + "_horizontal", side, top);

        ModelFile verticalResinFull = models().withExistingParent(nameLog + "_resin_full", mcLoc("block/cube"))
                .texture("down", top)
                .texture("up", top)
                .texture("north", resinFull)
                .texture("south", side)
                .texture("east", side)
                .texture("west", side)
                .texture("particle", side);

        ModelFile verticalResinEmpty = models().withExistingParent(nameLog + "_resin_empty", mcLoc("block/cube"))
                .texture("down", top)
                .texture("up", top)
                .texture("north", resinEmpty)
                .texture("south", side)
                .texture("east", side)
                .texture("west", side)
                .texture("particle", side);

        getVariantBuilder(log).forAllStates(state -> {
            Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
            boolean hasResin = state.getValue(RubberLog.RESIN);
            boolean isCollectable = state.getValue(RubberLog.COLLECTABLE);
            Direction facing = state.getValue(RubberLog.RESIN_FACING);

            if (!hasResin || axis != Direction.Axis.Y) {
                if (axis == Direction.Axis.Y) {
                    return ConfiguredModel.builder().modelFile(verticalLog).build();
                } else if (axis == Direction.Axis.Z) {
                    return ConfiguredModel.builder().modelFile(horizontalLog).rotationX(90).build();
                } else {
                    return ConfiguredModel.builder().modelFile(horizontalLog).rotationX(90).rotationY(90).build();
                }
            }

            ModelFile resinModel = isCollectable ? verticalResinFull : verticalResinEmpty;

            int rotY = switch (facing) {
                case EAST -> 90;
                case SOUTH -> 180;
                case WEST -> 270;
                default -> 0;
            };

            return ConfiguredModel.builder()
                    .modelFile(resinModel)
                    .rotationY(rotY)
                    .build();
        });
        simpleBlockItem(log, verticalLog);

        // Sapling
        Block sapling = ICREBlocks.RUBBER_SAPLING.get();
        String nameSapling = BuiltInRegistries.BLOCK.getKey(sapling).getPath();
        ModelFile modelSapling = models().cross(nameSapling, modLoc("block/rubber/" + nameSapling)).renderType("cutout");
        simpleBlock(sapling, modelSapling);
        itemModels().withExistingParent(nameSapling, mcLoc("item/generated"))
                .texture("layer0", modLoc("block/rubber/" + nameSapling));

        // Leaves
        Block leaves = ICREBlocks.RUBBER_LEAVES.get();
        String nameLeaves = BuiltInRegistries.BLOCK.getKey(leaves).getPath();
        ModelFile modelLeaves = models().cubeAll(nameLeaves, modLoc("block/rubber/" + nameLeaves)).renderType("cutout_mipped");
        simpleBlock(leaves, modelLeaves);
        simpleBlockItem(leaves, modelLeaves);
    }

    private void cableBlocks(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();

        ResourceLocation texture = modLoc("item/cable/" + name);
        itemModels().singleTexture(
                name,
                mcLoc("item/generated"),
                "layer0",
                texture
        );
    }
}
