package net.kaelos.icreexperimental.definitions;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.levelgen.feature.RubberFoliagePlacer;
import net.kaelos.icreexperimental.levelgen.feature.RubberTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ICREPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ICRE.MOD_ID);

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ICRE.MOD_ID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<RubberTrunkPlacer>> RUBBER_TRUNK_PLACER =
            TRUNK_PLACERS.register("rubber_trunk_placer", () -> new TrunkPlacerType<>(RubberTrunkPlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<RubberFoliagePlacer>> RUBBER_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("rubber_foliage_placer", () -> new FoliagePlacerType<>(RubberFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
        FOLIAGE_PLACERS.register(eventBus);
    }
}
