package earth.terrarium.worldofwonder.client;

import earth.terrarium.worldofwonder.WorldOfWonder;
import earth.terrarium.worldofwonder.client.renderer.entity.DandeLionRenderer;
import earth.terrarium.worldofwonder.client.renderer.entity.DandeLionSeedRenderer;
import earth.terrarium.worldofwonder.client.renderer.entity.StemChestBoatRenderer;
import earth.terrarium.worldofwonder.client.renderer.entity.model.DandeLionModel;
import earth.terrarium.worldofwonder.client.renderer.entity.model.DandelionHatModel;
import earth.terrarium.worldofwonder.client.renderer.tileentity.StemChestTileEntityRenderer;
import earth.terrarium.worldofwonder.common.compat.WonderQuarkCompat;
import earth.terrarium.worldofwonder.init.WonderEntities;
import earth.terrarium.worldofwonder.init.WonderTileEntities;
import earth.terrarium.worldofwonder.init.WonderWoodTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	public static ModelLayerLocation DANDELION_HAT = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion_hat"), "dandelion_hat");
	public static ModelLayerLocation DANDELION = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion"), "dandelion");
	public static ModelLayerLocation DANDELION_SEED = new ModelLayerLocation(new ResourceLocation(WorldOfWonder.MOD_ID, "dandelion_seed"), "dandelion_seed");

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(WonderEntities.DANDE_LION.get(), DandeLionRenderer::new);
		event.registerEntityRenderer(WonderEntities.DANDE_LION_SEED.get(), DandeLionSeedRenderer::new);
		event.registerEntityRenderer(WonderEntities.STEM_CHEST_BOAT.get(), StemChestBoatRenderer::new);
	}
	
	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {
		BlockEntityRenderers.register(WonderTileEntities.STEM_SIGN.get(), SignRenderer::new);
		BlockEntityRenderers.register(WonderTileEntities.HANGING_SIGN.get(), HangingSignRenderer::new);
		BlockEntityRenderers.register(WonderQuarkCompat.STEM_CHEST.get(), StemChestTileEntityRenderer::new);
		event.enqueueWork(() -> {
			Sheets.addWoodType(WonderWoodTypes.STEM);
		});
	}
	
	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DANDELION, DandeLionModel::createBodyLayer);
		event.registerLayerDefinition(DANDELION_HAT, () -> DandelionHatModel.createArmorLayer(new CubeDeformation(1)));
		event.registerLayerDefinition(DANDELION_SEED, DandeLionSeedRenderer::createSeed);
	}
	
}
