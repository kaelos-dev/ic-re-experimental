package net.kaelos.icreexperimental.definitions;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.item.MaterialItem;
import net.kaelos.icreexperimental.item.ToolItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ICREItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ICRE.MOD_ID);

    public static final DeferredItem<MaterialItem> SILVER_INGOT = ITEMS.register("silver_ingot", MaterialItem::new);
    public static final DeferredItem<MaterialItem> TIN_INGOT = ITEMS.register("tin_ingot", MaterialItem::new);
    public static final DeferredItem<MaterialItem> LEAD_INGOT = ITEMS.register("lead_ingot", MaterialItem::new);
    public static final DeferredItem<MaterialItem> BRONZE_INGOT = ITEMS.register("bronze_ingot", MaterialItem::new);
    public static final DeferredItem<MaterialItem> SILVER_PLATE = ITEMS.register("silver_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> TIN_PLATE = ITEMS.register("tin_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> LEAD_PLATE = ITEMS.register("lead_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> BRONZE_PLATE = ITEMS.register("bronze_plate", MaterialItem::new);

    public static final DeferredItem<ToolItem> HAMMER = ITEMS.register("hammer",
            () -> new ToolItem(80));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
