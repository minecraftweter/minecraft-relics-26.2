package net.minecraftweter.minecraftrelics;

import com.mojang.logging.LogUtils;
import net.minecraftweter.minecraftrelics.dataAttachment.ModDataAttachments;
import net.minecraftweter.minecraftrelics.gui.ModMenuTypes;
import net.minecraftweter.minecraftrelics.item.ModCreativeModeTabs;
import net.minecraftweter.minecraftrelics.item.ModDataComponents;
import net.minecraftweter.minecraftrelics.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MinecraftRelics.MOD_ID)
public class MinecraftRelics {
    public static final String MOD_ID = "minecraftrelics";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MinecraftRelics(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus); // Na Blocks en Items
        ModDataAttachments.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
