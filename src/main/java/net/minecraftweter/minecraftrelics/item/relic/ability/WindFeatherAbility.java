package net.minecraftweter.minecraftrelics.item.relic.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.ModDataComponents;
import net.minecraftweter.minecraftrelics.item.relic.RelicAbility;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class WindFeatherAbility implements RelicAbility {
    @Override
    public void onHurt(Player player, ItemStack relic, LivingIncomingDamageEvent damageEvent) {
        damageEvent.setAmount(
                damageEvent.getAmount() * (0.7f + ((relic.get(ModDataComponents.RELIC_LEVEL) - 1) * 0.02f))
        );
    }

    @Override
    public Component getTranslationComponent(ItemStack stack) {
        return Component.translatable(
                "tooltip." + MinecraftRelics.MOD_ID + ".ability.wind_feather",
                Component.literal(
                        (stack.get(ModDataComponents.RELIC_LEVEL) - 1) * 2 + 70 + "%"
                ).withColor(TextColor.YELLOW)
        );
    }
}
