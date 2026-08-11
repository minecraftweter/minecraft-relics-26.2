package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.network.chat.Component;
import net.minecraftweter.minecraftrelics.MinecraftRelics;

public enum RelicRarity {
    COMMON(""),
    UNCOMMON("§2"),
    RARE("§9"),
    EPIC("§5"),
    LEGENDARY("§6"),
    MYTHIC("§c");

    public static final String translationKey = "rarity." + MinecraftRelics.MOD_ID;
    public final Component name;
    public final String colorCode;
    RelicRarity(String colorCode) {
        this.name = Component.translatable(translationKey + "." + this.toString().toLowerCase());
        this.colorCode = colorCode;
    }
}
