package net.minecraftweter.minecraftrelics.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.gui.custom.RelicInventoryScreen;
import net.minecraftweter.minecraftrelics.networking.packet.ToggleRelicInventoryPacketC2S;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

@EventBusSubscriber(modid = MinecraftRelics.MOD_ID, value = Dist.CLIENT)
public class ModKeyMappings {
    public static final Lazy<KeyMapping> PRESS_RELIC_INVENTORY = Lazy.of(() -> new KeyMapping(
            "key." + MinecraftRelics.MOD_ID + ".relic_inventory",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            KeyMapping.Category.INVENTORY

    ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(PRESS_RELIC_INVENTORY.get());
    }

    @SubscribeEvent
    public static void informPlayer(EntityJoinLevelEvent event) {
        if(event.getEntity() instanceof Player player && event.getLevel().isClientSide()) {
            player.sendSystemMessage(Component.translatable(
                    "key." + MinecraftRelics.MOD_ID + ".relic_inventory.info",
                    getKeyString(PRESS_RELIC_INVENTORY.get().getKey())
            ));
        }
    }

    @SubscribeEvent
        // CLIENT
        Player player = Minecraft.getInstance().player;
        if(ModKeyMappings.PRESS_RELIC_INVENTORY.get().consumeClick() && player != null) {
            ClientPacketDistributor.sendToServer(new ToggleRelicInventoryPacketC2S());
        }
    }

    @SubscribeEvent
    public static void handleScreenKeyPress(ScreenEvent.KeyPressed.Post event) {
        if(event.getScreen() instanceof RelicInventoryScreen) {
            if (event.getKeyCode() == ModKeyMappings.PRESS_RELIC_INVENTORY.get().getKey().getValue()) {
                ClientPacketDistributor.sendToServer(new ToggleRelicInventoryPacketC2S());
            }
        }
    }

    private static String getKeyString(InputConstants.Key key) {
        return Arrays.stream(key.getName().split("\\."))
                .reduce((a, b) -> b)
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .orElse("");
    }
}
