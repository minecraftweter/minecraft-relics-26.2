package net.minecraftweter.minecraftrelics;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftweter.minecraftrelics.dataAttachment.ModDataAttachments;
import net.minecraftweter.minecraftrelics.keymapping.ModKeyMappings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = MinecraftRelics.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MinecraftRelics.MOD_ID, value = Dist.CLIENT)
public class MinecraftRelicsClient {
    public MinecraftRelicsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if(ModKeyMappings.PRESS_RELIC_INVENTORY.get().consumeClick()) {
            // CLIENT
            Player player = Minecraft.getInstance().player;
            player.sendSystemMessage(Component.literal(
                    "Inventory slot 1: " + player.getData(ModDataAttachments.RELIC_INVENTORY.get()).getResource(0)
            ));
        }
    }
}
