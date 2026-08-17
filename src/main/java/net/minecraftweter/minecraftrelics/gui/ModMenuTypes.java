package net.minecraftweter.minecraftrelics.gui;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryMenu;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryScreen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = MinecraftRelics.MOD_ID)
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            Registries.MENU, MinecraftRelics.MOD_ID
    );

    public static final DeferredHolder<MenuType<?>, MenuType<RelicInventoryMenu>> RELIC_INVENTORY_MENU = MENU_TYPES.register(
            "relic_inventory_menu", () -> new MenuType<>(RelicInventoryMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );


    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(RELIC_INVENTORY_MENU.get(), RelicInventoryScreen::new);
    }
}
