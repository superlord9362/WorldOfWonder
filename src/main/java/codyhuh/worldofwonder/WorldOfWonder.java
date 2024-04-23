package codyhuh.worldofwonder;

import java.util.concurrent.CompletableFuture;

import codyhuh.worldofwonder.client.ClientProxy;
import codyhuh.worldofwonder.client.WonderClientHandler;
import codyhuh.worldofwonder.common.CommonProxy;
import codyhuh.worldofwonder.common.compat.WonderQuarkCompat;
import codyhuh.worldofwonder.common.compat.WonderVanillaCompat;
import codyhuh.worldofwonder.common.util.RegistryHelper;
import codyhuh.worldofwonder.common.world.WonderFeatureAndBiomeGenerator;
import codyhuh.worldofwonder.common.world.biome.DandelionFieldsRegionProvider;
import codyhuh.worldofwonder.init.WonderBlocks;
import codyhuh.worldofwonder.init.WonderEntities;
import codyhuh.worldofwonder.init.WonderFoliagePlacers;
import codyhuh.worldofwonder.init.WonderItems;
import codyhuh.worldofwonder.init.WonderSounds;
import codyhuh.worldofwonder.init.WonderTabs;
import codyhuh.worldofwonder.init.WonderTileEntities;
import codyhuh.worldofwonder.init.WonderWoodTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.versions.forge.ForgeVersion;
import terrablender.api.Regions;

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


		REGISTRY_HELPER.getBlockSubHelper().register(bus);
		REGISTRY_HELPER.getItemSubHelper().register(bus);
		REGISTRY_HELPER.getBlockEntitySubHelper().register(bus);
        
		bus.addListener(this::setup);
        WonderQuarkCompat.BLOCK_REGISTER.register(bus);
        WonderQuarkCompat.ITEM_REGISTER.register(bus);
        WonderQuarkCompat.ENTITY_REGISTER.register(bus);

        WonderBlocks.REGISTRY.register(bus);
        WonderItems.REGISTRY.register(bus);
        WonderSounds.REGISTRY.register(bus);
        WonderEntities.REGISTRY.register(bus);
        WonderTileEntities.REGISTRY.register(bus);
        WonderFoliagePlacers.REGISTRY.register(bus);
        WonderTabs.REGISTRY.register(bus);

		
		bus.addListener(this::gatherData);
		
        bus.addListener(WonderEntities::setupAttributes);
        bus.addListener(WonderVanillaCompat::init);
        bus.addListener(WonderClientHandler::init);
    }
    
	private void setup(final FMLCommonSetupEvent event) {
		Regions.register(new DandelionFieldsRegionProvider(new ResourceLocation(MOD_ID, "dandelion_fields"), 1));
		event.enqueueWork(() -> {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(WonderBlocks.DANDE_LION_SPROUT.getId(), WonderBlocks.POTTED_DANDE_LION_SPROUT);
			WoodType.register(WonderWoodTypes.STEM);
		});
	}
	
	public void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean server = event.includeServer();
        dataGenerator.addProvider(server, new WonderFeatureAndBiomeGenerator(packOutput, lookupProvider));
    }
}
