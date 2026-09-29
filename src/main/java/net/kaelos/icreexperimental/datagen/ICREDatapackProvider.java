package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.worldgen.ICREBiomeModifiers;
import net.kaelos.icreexperimental.worldgen.ICREConfiguredFeatures;
import net.kaelos.icreexperimental.worldgen.ICREPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ICREDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ICREConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ICREPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ICREBiomeModifiers::bootstrap);

    public ICREDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ICRE.MOD_ID));
    }
}
