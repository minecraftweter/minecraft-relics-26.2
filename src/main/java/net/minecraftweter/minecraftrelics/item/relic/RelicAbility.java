package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftweter.minecraftrelics.data.ModDataComponents;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@SuppressWarnings("unused")
public abstract class RelicAbility {
    public final int activationLevel;
    public RelicAbility(int activationLevel) {
        this.activationLevel = activationLevel;
    }

    public void onUse(Player player, ItemStack stack) {}
    public void onAttack(Player player, ItemStack stack, AttackEntityEvent event) {}
    public void onHurt(Player player, ItemStack stack, LivingIncomingDamageEvent event) {}
    public void onKill(Player player, ItemStack stack, LivingDeathEvent event) {}
    public void onEquip(Player player, ItemStack stack) {}
    public void onUnequip(Player player, ItemStack stack) {}

    public boolean isActive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.RELIC_LEVEL, 1) >= this.activationLevel;
    }

    public abstract Component getTranslationComponent(ItemStack stack);
}
