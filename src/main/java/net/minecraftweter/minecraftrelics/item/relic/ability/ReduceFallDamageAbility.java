package net.minecraftweter.minecraftrelics.item.relic.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.data.ModDataComponents;
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
    public void onHurt(Player player, ItemStack stack, LivingIncomingDamageEvent event) {
        event.setAmount(event.getAmount() * getMultiplier(stack));
    }

    @Override
    public Component getTranslationComponent(ItemStack stack) {
        return Component.translatable(
                "tooltip." + MinecraftRelics.MOD_ID + ".ability.reduce_fall_damage",
                Component.literal(
                        (int) (getMultiplier(stack) * 100) + "%"
                ).withColor(TextColor.YELLOW)
        );
    }

    private float getMultiplier(ItemStack stack) {
        return Math.min(
                startValue + ((stack.getOrDefault(ModDataComponents.RELIC_LEVEL, 1) - activationLevel) * levelBonusValue),
                maxValue
        );
    }
}
