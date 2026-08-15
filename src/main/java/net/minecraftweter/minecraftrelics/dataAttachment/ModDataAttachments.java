package net.minecraftweter.minecraftrelics.dataAttachment;

import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> DATA_ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, MinecraftRelics.MOD_ID
    );

    public static final Supplier<AttachmentType<? extends ItemStacksResourceHandler>> RELIC_INVENTORY = DATA_ATTACHMENT_TYPES.register(
            "relic_inventory", () -> AttachmentType.serializable(() -> new ItemStacksResourceHandler(6) {
                @Override
                protected int getCapacity(int index, @NonNull ItemResource resource) {
                    return 1;
                }

                @Override
                protected void onContentsChanged(int index, @NonNull ItemStack previousContents) {
                    super.onContentsChanged(index, previousContents);
                }
            }).build()
    );

    public static void register(IEventBus eventBus) {
        DATA_ATTACHMENT_TYPES.register(eventBus);
    }
}
