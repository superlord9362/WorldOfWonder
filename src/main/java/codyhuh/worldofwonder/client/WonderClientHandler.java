package codyhuh.worldofwonder.client;

import codyhuh.worldofwonder.client.renderer.entity.DandeLionRenderer;
import codyhuh.worldofwonder.client.renderer.entity.DandeLionSeedRenderer;
import codyhuh.worldofwonder.client.renderer.entity.StemBoatRenderer;
import codyhuh.worldofwonder.client.renderer.tileentity.StemChestTileEntityRenderer;
import codyhuh.worldofwonder.common.compat.WonderQuarkCompat;
import codyhuh.worldofwonder.init.WonderBlocks;
import codyhuh.worldofwonder.init.WonderEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class WonderClientHandler {
    @SuppressWarnings("deprecation")
	public static void init(FMLClientSetupEvent event) {
        EntityRenderers.register(WonderEntities.STEM_BOAT.get(), StemBoatRenderer::new);
        EntityRenderers.register(WonderEntities.DANDE_LION.get(), DandeLionRenderer::new);
        EntityRenderers.register(WonderEntities.DANDE_LION_SEED.get(), DandeLionSeedRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.STEM_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.STEM_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.DANDE_LION_SPROUT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WonderBlocks.POTTED_DANDE_LION_SPROUT.get(), RenderType.cutout());

        BlockEntityRenderers.register(WonderQuarkCompat.STEM_CHEST.get(), StemChestTileEntityRenderer::new);
        BlockEntityRenderers.register(WonderQuarkCompat.STEM_TRAPPED_CHEST.get(), StemChestTileEntityRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(WonderQuarkCompat.STEM_LADDER.get(), RenderType.cutout());
    }
}
