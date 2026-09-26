package net.kaelos.icreexperimental.definitions;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.item.MaterialItem;
import net.kaelos.icreexperimental.item.ToolItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ICRECreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ICRE.MOD_ID);

    @SuppressWarnings("unused")
    public static Supplier<CreativeModeTab> MATERIALS = TABS.register(
            "material", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ICREItems.SILVER_INGOT.get()))
                    .title(Component.translatable("itemGroup." + ICRE.MOD_ID + ".materials"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        ICREItems.ITEMS.getEntries().stream()
                                .map(DeferredHolder::get)
                                .filter(item -> item instanceof MaterialItem)
                                .forEach(output::accept);
                    })).build());

    @SuppressWarnings("unused")
    public static Supplier<CreativeModeTab> TOOLS = TABS.register(
            "tools", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ICREItems.HAMMER.get()))
                    .title(Component.translatable("itemGroup." + ICRE.MOD_ID + ".tools"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        ICREItems.ITEMS.getEntries().stream()
                                .map(DeferredHolder::get)
                                .filter(item -> item instanceof ToolItem)
                                .forEach(output::accept);
                    })).build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
