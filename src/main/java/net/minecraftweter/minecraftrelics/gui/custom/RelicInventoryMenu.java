package net.minecraftweter.minecraftrelics.gui.custom;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.data.ModDataAttachments;
import net.minecraftweter.minecraftrelics.gui.ModMenuTypes;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;
import net.minecraftweter.minecraftrelics.player.RelicInventory;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import org.jspecify.annotations.NonNull;

public class RelicInventoryMenu extends AbstractContainerMenu {

    public RelicInventoryMenu(int containerId, Inventory playerInv) {
        super(ModMenuTypes.RELIC_INVENTORY_MENU.get(), containerId);
        Player player = playerInv.player;
        RelicInventory relicInv = player.getData(ModDataAttachments.RELIC_INVENTORY.get());

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);

        addRelicSlot(relicInv, 0, 58, 21);
        addRelicSlot(relicInv, 1, 80, 21);
        addRelicSlot(relicInv, 2, 102, 21);
        addRelicSlot(relicInv, 3, 58, 45);
        addRelicSlot(relicInv, 4, 80, 45);
        addRelicSlot(relicInv, 5, 102, 45);
    }

    private void addRelicSlot(RelicInventory inv, int slot, int x, int y) {
        this.addSlot(new ResourceHandlerSlot(inv, inv::set, slot, x, y) {
            @Override
            public boolean mayPlace(@NonNull ItemStack itemStack) {
                if(!(itemStack.getItem() instanceof RelicItem)) {
                    return false;
                }
                return super.mayPlace(itemStack);
            }
        });
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    /* CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
     * must assign a slot number to each of the slots used by the GUI.
     * For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
     * Each time we add a Slot to the container, it automatically increases the slotIndex, which means
     *  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
     *  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
     *  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
     */
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = RelicInventory.SIZE;  // must be the number of slots you have!
    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
