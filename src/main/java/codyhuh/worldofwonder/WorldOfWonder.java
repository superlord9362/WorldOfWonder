package codyhuh.worldofwonder;

import java.util.concurrent.CompletableFuture;

import codyhuh.worldofwonder.client.ClientProxy;
import codyhuh.worldofwonder.core.other.WonderClientCompat;
import codyhuh.worldofwonder.core.other.WonderModelLayers;
import codyhuh.worldofwonder.client.renderer.entity.DandeLionRenderer;
import codyhuh.worldofwonder.client.renderer.entity.DandeLionSeedRenderer;
import codyhuh.worldofwonder.client.renderer.entity.model.DandeLionModel;
import codyhuh.worldofwonder.client.renderer.entity.model.DandelionHatModel;
import codyhuh.worldofwonder.common.CommonProxy;
import codyhuh.worldofwonder.core.other.WonderCompat;
import codyhuh.worldofwonder.core.data.server.WonderDatapackBuiltinEntriesProvider;
import codyhuh.worldofwonder.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.versions.forge.ForgeVersion;

@Mod(WorldOfWonder.MOD_ID)
@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID)
public class WorldOfWonder {
    public static final TagKey<Item> DANDELION = TagKey.create(Registries.ITEM, new ResourceLocation(ForgeVersion.MOD_ID, "dandelion"));
    public static final String MOD_ID = "worldofwonder";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	@SuppressWarnings("deprecation")
	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	//private static int currentNetworkId;

    public WorldOfWonder() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(bus);
        WonderFeatures.FEATURES.register(bus);
        WonderFoliagePlacers.REGISTRY.register(bus);
        WonderTabs.REGISTRY.register(bus);

        bus.addListener(this::clientSetup);
        bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            WonderItems.setupTabEditors();
            WonderBlocks.setupTabEditors();
            bus.addListener(this::registerLayerDefinitions);
            bus.addListener(this::registerRenderers);
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            WonderClientCompat.registerCompat();
        });
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(WonderBlocks.DANDE_LION_SPROUT.getId(), WonderBlocks.POTTED_DANDE_LION_SPROUT);
            WonderCompat.registerCompat();
        });
    }
	
	public void dataSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean server = event.includeServer();
        dataGenerator.addProvider(server, new WonderDatapackBuiltinEntriesProvider(packOutput, lookupProvider));
    }

    @OnlyIn(Dist.CLIENT)
    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WonderModelLayers.DANDELION, DandeLionModel::createBodyLayer);
        event.registerLayerDefinition(WonderModelLayers.DANDELION_HAT, () -> DandelionHatModel.createArmorLayer(new CubeDeformation(1)));
        event.registerLayerDefinition(WonderModelLayers.DANDELION_SEED, DandeLionSeedRenderer::createSeed);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WonderEntityTypes.DANDE_LION.get(), DandeLionRenderer::new);
        event.registerEntityRenderer(WonderEntityTypes.DANDE_LION_SEED.get(), DandeLionSeedRenderer::new);
    }
}
