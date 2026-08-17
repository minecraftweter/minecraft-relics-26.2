package net.minecraftweter.minecraftrelics.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraftweter.minecraftrelics.MinecraftRelics;

public record ToggleRelicInventoryPacketC2S() implements CustomPacketPayload {
    public static final Type<ToggleRelicInventoryPacketC2S> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(MinecraftRelics.MOD_ID, "toggle_relic_inventory_packet")
    );

    public static final StreamCodec<ByteBuf, ToggleRelicInventoryPacketC2S> STREAM_CODEC = StreamCodec.unit(
            new ToggleRelicInventoryPacketC2S()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
