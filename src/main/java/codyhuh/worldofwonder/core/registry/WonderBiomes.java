package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.world.biome.DandelionFieldsBiomeDecorator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WonderBiomes {
	
	public static final ResourceKey<Biome> DANDELION_FIELDS = register("dandelion_fields");
	
	public static void bootstrap(BootstapContext<Biome> bootstapContext) {
		HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
		bootstapContext.register(DANDELION_FIELDS, DandelionFieldsBiomeDecorator.decorateDandelionFields(holderGetter, holdergetter1));
	}
	
	private static ResourceKey<Biome> register(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(WorldOfWonder.MOD_ID, name));
	}

}
