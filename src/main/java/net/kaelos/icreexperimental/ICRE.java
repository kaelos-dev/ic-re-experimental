package net.kaelos.icreexperimental;

import net.kaelos.icreexperimental.definitions.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ICRE.MOD_ID)
public class ICRE {
    public static final String MOD_ID = "icre";

    public static ResourceLocation makeId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public ICRE(final IEventBus eventBus) {
        ICREBlocks.register(eventBus);
        ICREItems.register(eventBus);
        ICRECreativeModeTabs.register(eventBus);
        ICREPlacerTypes.register(eventBus);
        ICRESounds.register(eventBus);
    }
}
