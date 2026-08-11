package net.minecraftweter.minecraftrelics.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.relic.RelicCategory;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;
import net.minecraftweter.minecraftrelics.item.relic.RelicRarity;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModTranslationProvider extends LanguageProvider {

    public ModTranslationProvider(PackOutput output) {
        super(output, MinecraftRelics.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (CreativeModeTab tab : BuiltInRegistries.CREATIVE_MODE_TAB) {
            Identifier id = BuiltInRegistries.CREATIVE_MODE_TAB.getKey(tab);
            assert id != null;
            if(id.getNamespace().equals(MinecraftRelics.MOD_ID)) {
                add(id.toLanguageKey("creativemodetab"), formatName(id.getPath()));
            }
        }
        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if(id.getNamespace().equals(MinecraftRelics.MOD_ID)) {
                if (item instanceof RelicItem relicItem) {
                    add(item, relicItem.rarity.colorCode + formatName(id.getPath()));
                } else {
                    add(item, formatName(id.getPath()));
                }
            }
        }

        for(RelicRarity rarity : RelicRarity.values()) {
            add(
                    RelicRarity.translationKey + "." + rarity.toString().toLowerCase(),
                    rarity.colorCode + formatName(rarity.toString().toLowerCase())
            );
        }
        for(RelicCategory category : RelicCategory.values()) {
            add(
                    RelicCategory.translationKey + "." + category.toString().toLowerCase(),
                    category.colorCode + formatName(category.toString().toLowerCase())
            );
        }

        add(RelicItem.tooltipTranslationKey + ".rarity", "Rarity: %s");
        add(RelicItem.tooltipTranslationKey + ".category", "Category: %s");
        add(RelicItem.tooltipTranslationKey + ".xp", "XP: %s");
        add(RelicItem.tooltipTranslationKey + ".level", "Level: %s");
        add(RelicItem.tooltipTranslationKey + ".more_info", "Press §eShift§r for more information!");

        add(RelicItem.tooltipTranslationKey + ".ability.windfeather", "Reduces fall damage by %s");
    }

    private static String formatName(String name) {
        return Arrays.stream(name.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
