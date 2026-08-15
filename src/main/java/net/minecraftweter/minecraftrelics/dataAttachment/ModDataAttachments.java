package net.minecraftweter.minecraftrelics.dataAttachment;

import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.player.RelicInventory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> DATA_ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, MinecraftRelics.MOD_ID
    );

    public static final Supplier<AttachmentType<RelicInventory>> RELIC_INVENTORY = DATA_ATTACHMENT_TYPES.register(
            "relic_inventory", () -> AttachmentType.serializable(() -> new RelicInventory(6)).build()
    );

    public static void register(IEventBus eventBus) {
        DATA_ATTACHMENT_TYPES.register(eventBus);
    }
}
