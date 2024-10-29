package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.core.other.WonderGeneration;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WonderBiomes {
	public static final ResourceKey<Biome> DANDELION_FIELDS = createKey("dandelion_fields");

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(DANDELION_FIELDS, dandelionFields(features, carvers));
	}

	public static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(WorldOfWonder.MOD_ID, name));
	}

	private static Biome dandelionFields(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		WonderGeneration.dandelionFields(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WonderEntityTypes.DANDE_LION.get(), 30, 2, 3));
		BiomeDefaultFeatures.farmAnimals(spawns);
		BiomeDefaultFeatures.commonSpawns(spawns);
		Music music = Musics.createGameMusic(Holder.direct(WonderSounds.MUSIC_BIOME_DANDELION_FIELDS.get()));
		return biome(true, 0.7F, 0.8F, 7907327, 7578511, 8431984,
				4159204, 329011, 12638463, spawns, generation, music);
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, int skyColor, int grassColor, int foliageColor, int waterColor, int waterFogColor, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation).temperature(temperature).downfall(downfall)
				.specialEffects((new BiomeSpecialEffects.Builder())
//						.grassColorOverride(grassColor).foliageColorOverride(foliageColor)
						.waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor)
						.skyColor(skyColor).ambientMoodSound(
								AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music)
						.build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}
}
