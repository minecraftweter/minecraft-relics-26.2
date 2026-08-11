package net.minecraftweter.minecraftrelics.item.relic;

import net.minecraft.network.chat.Component;
import net.minecraftweter.minecraftrelics.MinecraftRelics;

public enum RelicCategory {
    COMBAT("§c"),
    DEFENSE("§9"),
    MOVEMENT("§2"),
    UTILITY("§e");

    public static final String translationKey = "category." + MinecraftRelics.MOD_ID;
    public final Component name;
    public final String colorCode;
    RelicCategory(String colorCode) {
        this.name = Component.translatable(translationKey + "." + this.toString().toLowerCase());
        this.colorCode = colorCode;
    }
}
