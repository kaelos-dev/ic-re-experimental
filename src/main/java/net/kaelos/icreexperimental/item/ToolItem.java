package net.kaelos.icreexperimental.item;

import net.kaelos.icreexperimental.ICRE;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ToolItem extends Item {
    public ToolItem(int durability) {
        super(new Properties()
                .durability(durability));
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public @NotNull ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        ItemStack remainder = stack.copy();
        remainder.setDamageValue(remainder.getDamageValue() + 1);
        return remainder;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        int maxDamage = stack.getMaxDamage();
        int currentDurability = maxDamage - stack.getDamageValue();

        tooltipComponents.add(
                Component.translatable("tooltip." + ICRE.MOD_ID + ".uses", currentDurability, maxDamage)
                        .withStyle(ChatFormatting.GRAY)
        );

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
