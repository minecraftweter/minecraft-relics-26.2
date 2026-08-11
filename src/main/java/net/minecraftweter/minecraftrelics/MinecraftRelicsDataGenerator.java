package net.minecraftweter.minecraftrelics;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftweter.minecraftrelics.datagen.ModModelProvider;
import net.minecraftweter.minecraftrelics.datagen.ModTranslationProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = MinecraftRelics.MOD_ID)
public class MinecraftRelicsDataGenerator {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true, new ModTranslationProvider(packOutput));
        generator.addProvider(true, new ModModelProvider(packOutput));
    }
}
