package net.minecraftweter.minecraftrelics.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, MinecraftRelics.MOD_ID
    );

    public static final Supplier<CreativeModeTab> RELICS = CREATIVE_MODE_TABS.register("relics",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.WIND_FEATHER_RELIC.get()))
                    .title(Component.translatable("creativemodetab." + MinecraftRelics.MOD_ID + ".relics"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (Item item : BuiltInRegistries.ITEM) {
                            if(item instanceof RelicItem) {
                                output.accept(item);
                            }
                        }
                    }).build()
    );


    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
