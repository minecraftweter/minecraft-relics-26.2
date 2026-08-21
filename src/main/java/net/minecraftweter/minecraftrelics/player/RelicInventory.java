package net.minecraftweter.minecraftrelics.player;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.data.ModDataAttachments;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryMenu;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@EventBusSubscriber(modid = MinecraftRelics.MOD_ID)
public class RelicInventory extends ItemStacksResourceHandler implements MenuProvider {
    public static final int SIZE = 6;
    public RelicInventory() {
        super(SIZE);
    }

    /* Menu */

    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("menu." + MinecraftRelics.MOD_ID + ".relic_inventory");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, @NonNull Inventory inventory, @NonNull Player player) {
        return new RelicInventoryMenu(i, inventory);
    }

    /* Events */

    @SubscribeEvent
    public static void playerDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player && !((ServerLevel) entity.level()).getGameRules().get(GameRules.KEEP_INVENTORY)) {
            RelicInventory relicInventory = player.getData(ModDataAttachments.RELIC_INVENTORY);
            relicInventory.copyToList().forEach(stack -> player.drop(stack, false));
            player.setData(ModDataAttachments.RELIC_INVENTORY, new RelicInventory());
        }
    }

    @SubscribeEvent
    public static void playerCloneEvent(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();
        newPlayer.setData(
                ModDataAttachments.RELIC_INVENTORY,
                oldPlayer.getData(ModDataAttachments.RELIC_INVENTORY)
        );
    }


}
