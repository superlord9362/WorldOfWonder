package codyhuh.worldofwonder.core.other;

import codyhuh.worldofwonder.core.registry.WonderBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class WonderClientCompat {
    public static void registerCompat() {
        registerRenderLayers();
    }

    private static void registerRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.STEM_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.STEM_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.DANDE_LION_SPROUT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.MELLOW_PETALS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.POTTED_DANDE_LION_SPROUT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.STEM_LADDER.get(), RenderType.cutout());
    }
}
