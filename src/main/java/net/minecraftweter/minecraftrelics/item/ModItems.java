package net.minecraftweter.minecraftrelics.item;

import net.minecraft.world.item.Item;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.relic.RelicCategory;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;
import net.minecraftweter.minecraftrelics.item.relic.RelicRarity;
import net.minecraftweter.minecraftrelics.item.relic.ability.IncreaseMovementSpeedAbility;
import net.minecraftweter.minecraftrelics.item.relic.ability.ReduceFallDamageAbility;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MinecraftRelics.MOD_ID);

    public static final DeferredItem<Item> WIND_FEATHER_RELIC = ITEMS.register(
            "wind_feather_relic", () -> new RelicItem(
                    "wind_feather_relic", RelicRarity.RARE, RelicCategory.MOVEMENT,
                    new ReduceFallDamageAbility(1, 0.7f, 0.02f, 0.9f),
                    new IncreaseMovementSpeedAbility(2, 0.1f, 0.02f, 0.7f)
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
