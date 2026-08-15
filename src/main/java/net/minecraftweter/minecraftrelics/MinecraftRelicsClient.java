package net.minecraftweter.minecraftrelics;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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
        // Some client setup code
        MinecraftRelics.LOGGER.info("HELLO FROM CLIENT SETUP");
        MinecraftRelics.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
