package net.minecraftweter.minecraftrelics.player;

import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;

import java.util.Arrays;

public class RelicInventory {

    private final ItemStack[] relicSlots = {
            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY
    };

    public ItemStack getRelicSlot(int slot) {
        return relicSlots[slot];
    }

    public void setRelicSlot(int slot, ItemStack stack) {
        if (stack.isEmpty() || stack.getItem() instanceof RelicItem) {
            relicSlots[slot] = stack;
        }
    }

    public void removeStack(int slot) {
        relicSlots[slot] = ItemStack.EMPTY;
    }

    public boolean isEmpty(int slot) {
        return relicSlots[slot].isEmpty();
    }

    public boolean isInventoryEmpty() {
        return Arrays.stream(relicSlots).allMatch(ItemStack::isEmpty);
    }
}