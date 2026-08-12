package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.item.ModDataComponents;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

public interface RelicAbility {
    default void onUse(Player player, ItemStack relic) {}
    default void onAttack(Player player, ItemStack relic, AttackEntityEvent attackEvent) {}
    default void onHurt(Player player, ItemStack relic, LivingIncomingDamageEvent damageEvent) {}
    default void onKill(Player player, ItemStack relic, LivingDeathEvent deathEvent, LivingIncomingDamageEvent damageEvent) {}
    default void onTick(Player player, ItemStack relic) {}
    default void onEquip(Player player, ItemStack relic) {}
    default void onUnequip(Player player, ItemStack relic) {}
    Component getTranslationComponent(ItemStack stack);

    record RelicAbilityFromLevel(int level, RelicAbility ability) {
        public boolean isActive(ItemStack stack) {
            return stack.get(ModDataComponents.RELIC_LEVEL) >= level;
        }
    }
}
