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
    public static final DeferredItem<MaterialItem> STEEL_INGOT = ITEMS.register("steel_ingot", MaterialItem::new);

    public static final DeferredItem<MaterialItem> TIN_PLATE = ITEMS.register("tin_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> LEAD_PLATE = ITEMS.register("lead_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> BRONZE_PLATE = ITEMS.register("bronze_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> STEEL_PLATE = ITEMS.register("steel_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> IRON_PLATE = ITEMS.register("iron_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> COPPER_PLATE = ITEMS.register("copper_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> GOLD_PLATE = ITEMS.register("gold_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> LAPIS_PLATE = ITEMS.register("lapis_plate", MaterialItem::new);
    public static final DeferredItem<MaterialItem> OBSIDIAN_PLATE = ITEMS.register("obsidian_plate", MaterialItem::new);

    public static final DeferredItem<ToolItem> HAMMER = ITEMS.register("hammer",
            () -> new ToolItem(80));
    public static final DeferredItem<ToolItem> CUTTER = ITEMS.register("cutter",
            () -> new ToolItem(60));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
