package net.minecraftweter.minecraftrelics.gui.custom;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftweter.minecraftrelics.MinecraftRelics;

public class RelicInventoryScreen extends AbstractContainerScreen<RelicInventoryMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(
            MinecraftRelics.MOD_ID, "textures/gui/relic_inventory.png"
    );
    public RelicInventoryScreen(RelicInventoryMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                GUI_TEXTURE,
                (width - imageWidth) / 2, (height - imageHeight) / 2,
                0, 0,
                this.imageWidth, this.imageHeight,
                256, 256
        );
    }
}
