package net.minecraftweter.minecraftrelics.networking;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryMenu;
import net.minecraftweter.minecraftrelics.networking.packet.ToggleRelicInventoryPacketC2S;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    // SERVER

    public static void toggleRelicInventory(
            ToggleRelicInventoryPacketC2S toggleRelicInventoryPacketC2S,

            IPayloadContext context
    ) {
        ServerPlayer player = (ServerPlayer) context.player();
        if(!(player.containerMenu instanceof RelicInventoryMenu)) {
            player.openMenu(new SimpleMenuProvider(
                    (id, inv, p) -> new RelicInventoryMenu(id, inv),
                    Component.literal("Relics")
            ));
        } else {
            player.closeContainer();
        }
    }
}
