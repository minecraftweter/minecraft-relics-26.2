package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.data.ModDataAttachments;
import net.minecraftweter.minecraftrelics.data.ModDataComponents;
import net.minecraftweter.minecraftrelics.player.RelicInventory;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RelicItem extends Item {
    public final RelicRarity rarity;
    public final RelicCategory category;
    public final RelicAbility[] abilities;

    public static final String tooltipTranslationKey = "tooltip." + MinecraftRelics.MOD_ID;

    public RelicItem(
            String name,
            RelicRarity rarity,
            RelicCategory category,
            RelicAbility ... abilities
    ) {
        super(
            new Item.Properties()
                    .stacksTo(1)
                    .component(ModDataComponents.RELIC_XP.get(), 0)
                    .component(ModDataComponents.RELIC_LEVEL.get(), 1)
                    .setId(
                        ResourceKey.create(
                                Registries.ITEM,
                                Identifier.fromNamespaceAndPath(MinecraftRelics.MOD_ID, name)
                        )
                    )
        );
        this.rarity = rarity;
        this.category = category;
        this.abilities = abilities;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(
            @NonNull ItemStack itemStack, @NonNull TooltipContext context,
            @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder,
            @NonNull TooltipFlag tooltipFlag
    ) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable(tooltipTranslationKey + ".rarity", rarity.name));
            builder.accept(Component.translatable(tooltipTranslationKey + ".category", category.name));
            builder.accept(Component.empty());
            for(RelicAbility ability : abilities) {
                if(ability.isActive(itemStack)) {
                    builder.accept(ability.getTranslationComponent(itemStack));
                }
            }
            builder.accept(Component.empty());
            builder.accept(Component.translatable(tooltipTranslationKey + ".level",
                    Objects.requireNonNull(itemStack.get(ModDataComponents.RELIC_LEVEL.get())).toString()
            ));
            builder.accept(Component.translatable(tooltipTranslationKey + ".xp",
                    Objects.requireNonNull(itemStack.get(ModDataComponents.RELIC_XP.get())).toString()
            ));
        } else {
            builder.accept(Component.translatable(tooltipTranslationKey + ".more_info"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    public static void callAbilityMethods(Player player, BiConsumer<RelicAbility, ItemStack> consumer) {
        RelicInventory relicInventory = player.getData(ModDataAttachments.RELIC_INVENTORY.get());
        relicInventory.copyToList().stream().filter(stack -> !stack.isEmpty()).collect(Collectors.toMap(
                ItemStack::getItem,
                Function.identity(),
                (a, b) -> a.getOrDefault(ModDataComponents.RELIC_LEVEL.get(), 1)
                        >= b.getOrDefault(ModDataComponents.RELIC_LEVEL.get(), 1) ? a : b
        )).values().forEach(stack -> {
            for(RelicAbility ability : ((RelicItem) stack.getItem()).abilities){
                consumer.accept(ability, stack);
            }
        });
    }
}
