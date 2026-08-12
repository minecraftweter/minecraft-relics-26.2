package net.minecraftweter.minecraftrelics.item.relic.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.ModDataComponents;
import net.minecraftweter.minecraftrelics.item.relic.RelicAbility;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class ReduceFallDamageAbility extends RelicAbility {
    public final float startValue, levelBonusValue, maxValue;
    public ReduceFallDamageAbility(int activationLevel, float startValue, float levelBonusValue, float maxValue) {
        super(activationLevel);
        this.startValue = startValue;
        this.levelBonusValue = levelBonusValue;
        this.maxValue = maxValue;
    }

    @Override
    public void onHurt(Player player, ItemStack stack, LivingIncomingDamageEvent damageEvent) {
        damageEvent.setAmount(damageEvent.getAmount() * getMultiplier(stack));
    }

    @Override
    public Component getTranslationComponent(ItemStack stack) {
        return Component.translatable(
                "tooltip." + MinecraftRelics.MOD_ID + ".ability.wind_feather",
                Component.literal(
                        (int) (getMultiplier(stack) * 100) + "%"
                ).withColor(TextColor.YELLOW)
        );
    }

    private float getMultiplier(ItemStack stack) {
        return Math.min(startValue + ((stack.get(ModDataComponents.RELIC_LEVEL) - activationLevel) * levelBonusValue), maxValue);
    }
}
