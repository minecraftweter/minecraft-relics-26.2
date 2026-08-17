package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.world.entity.player.Player;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber(modid = MinecraftRelics.MOD_ID)
public final class RelicEvents {
    @SubscribeEvent
    public static void onHurt(LivingIncomingDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(player.level().isClientSide()) return;
            RelicItem.callAbilityMethods(player, (relicAbility,  itemStack) -> {
                relicAbility.onHurt(player, itemStack, event);
            });
        }
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if(player.level().isClientSide()) return;
        RelicItem.callAbilityMethods(player, (relicAbility, itemStack) -> {
            relicAbility.onAttack(player, itemStack, event);
        });
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player) {
            if(player.level().isClientSide()) return;
            RelicItem.callAbilityMethods(player, (relicAbility, itemStack) -> {
                relicAbility.onKill(player, itemStack, event);
            });
        }
    }
}
