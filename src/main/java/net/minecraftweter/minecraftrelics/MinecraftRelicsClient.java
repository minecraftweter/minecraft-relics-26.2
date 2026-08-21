package net.minecraftweter.minecraftrelics;

import net.minecraftweter.minecraftrelics.networking.ClientPayloadHandler;
import net.minecraftweter.minecraftrelics.networking.packet.ToggleRelicInventoryPacketC2S;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(value = MinecraftRelics.MOD_ID, dist = Dist.CLIENT)
// CLIENT EVENTS ONLY
@EventBusSubscriber(modid = MinecraftRelics.MOD_ID, value = Dist.CLIENT)
public class MinecraftRelicsClient {
    public MinecraftRelicsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {}

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.MAIN);

        registrar.playToServer(
                ToggleRelicInventoryPacketC2S.TYPE,
                ToggleRelicInventoryPacketC2S.STREAM_CODEC,
                ClientPayloadHandler::toggleRelicInventory
        );
    }
}
