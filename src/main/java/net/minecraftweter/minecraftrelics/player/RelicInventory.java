package net.minecraftweter.minecraftrelics.player;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryMenu;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.Nullable;

public class RelicInventory extends ItemStacksResourceHandler implements MenuProvider {
    public static final int SIZE = 6;
    public RelicInventory() {
        super(SIZE);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu." + MinecraftRelics.MOD_ID + ".relic_inventory");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new RelicInventoryMenu(i, inventory);
    }

}
