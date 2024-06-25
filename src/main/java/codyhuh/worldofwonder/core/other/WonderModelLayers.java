package codyhuh.worldofwonder.core.other;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.client.renderer.entity.DandeLionSeedRenderer;
import codyhuh.worldofwonder.client.renderer.entity.model.DandeLionModel;
import codyhuh.worldofwonder.client.renderer.entity.model.DandelionHatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WonderModelLayers {
	public static ModelLayerLocation DANDELION_HAT = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion_hat"), "dandelion_hat");
	public static ModelLayerLocation DANDELION = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion"), "dandelion");
	public static ModelLayerLocation DANDELION_SEED = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion_seed"), "dandelion_seed");
}
