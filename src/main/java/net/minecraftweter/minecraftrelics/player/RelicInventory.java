package net.minecraftweter.minecraftrelics.player;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class RelicInventory extends ItemStacksResourceHandler {
    public RelicInventory(int size) {
        super(size);
    }

    @Override
    protected int getCapacity(int index, ItemResource resource) {
        return 1;
    }
}
