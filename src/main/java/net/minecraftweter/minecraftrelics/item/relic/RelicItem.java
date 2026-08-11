package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class RelicItem extends Item {
    public final RelicRarity rarity;
    public final RelicCategory category;
    public final RelicAbility[] abilities;

    public static final String tooltipTranslationKey = "tooltip." + MinecraftRelics.MOD_ID;

    public RelicItem(String name, RelicRarity rarity, RelicCategory category) {
        super(
            new Item.Properties()
                    .stacksTo(1)
                    .setId(
                        ResourceKey.create(
                                Registries.ITEM,
                                Identifier.fromNamespaceAndPath(MinecraftRelics.MOD_ID, name)
                        )
                    )
        );
        this.rarity = rarity;
        this.category = category;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context,
                                @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder,
                                @NonNull TooltipFlag tooltipFlag
    ) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable(tooltipTranslationKey + ".rarity", rarity.name));
            builder.accept(Component.translatable(tooltipTranslationKey + ".category", category.name));
            builder.accept(Component.empty());
            for(RelicAbility ability : abilities) {
                builder.accept(ability.getTranslationComponent(itemStack));
            }
            builder.accept(Component.empty());
            builder.accept(Component.translatable(tooltipTranslationKey + ".level",
                    itemStack.get(ModDataComponents.RELIC_LEVEL.get()).toString()
            ));
            builder.accept(Component.translatable(tooltipTranslationKey + ".xp",
                    itemStack.get(ModDataComponents.RELIC_XP.get()).toString()
            ));
        } else {
            builder.accept(Component.translatable(tooltipTranslationKey + ".more_info"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
