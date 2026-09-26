package net.kaelos.icreexperimental.datagen;

import net.kaelos.icreexperimental.ICRE;
import net.kaelos.icreexperimental.definitions.ICREItems;
import net.kaelos.icreexperimental.item.MaterialItem;
import net.kaelos.icreexperimental.item.ToolItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ICREItemModelProvider extends ItemModelProvider {
    public ICREItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ICRE.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        ICREItems.ITEMS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(item -> item instanceof MaterialItem)
                .forEach(this::materialItem);

        ICREItems.ITEMS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(item -> item instanceof ToolItem)
                .forEach(this::toolItem);
    }

    private void materialItem(Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(name, "item/generated")
                .texture("layer0", ICRE.makeId("item/material/" + name));
    }

    private void toolItem(Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(name, "item/handheld")
                .texture("layer0", ICRE.makeId("item/tool/" + name));
    }
}
