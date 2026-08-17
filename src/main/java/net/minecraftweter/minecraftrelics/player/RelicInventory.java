package net.minecraftweter.minecraftrelics.player;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.minecraft.world.MenuProvider;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class RelicInventory extends ItemStacksResourceHandler {
    public RelicInventory(int size) {
        super(size);
    public static final int SIZE = 6;
    public RelicInventory() {
        super(SIZE);
    }

    @Override
    protected int getCapacity(int index, ItemResource resource) {
        return 1;
    }
}
