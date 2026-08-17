package net.minecraftweter.minecraftrelics.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftweter.minecraftrelics.MinecraftRelics;
import net.minecraftweter.minecraftrelics.item.relic.RelicItem;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MinecraftRelics.MOD_ID);
    }

    @Override
    protected void registerModels(
            @NonNull BlockModelGenerators blockModels,
            @NonNull ItemModelGenerators itemModels
    ) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof RelicItem) {
                itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
            }
        }
    }
}