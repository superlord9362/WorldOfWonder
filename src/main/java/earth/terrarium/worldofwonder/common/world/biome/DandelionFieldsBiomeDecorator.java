package earth.terrarium.worldofwonder.common.world.biome;

import earth.terrarium.worldofwonder.init.WonderEntities;
import earth.terrarium.worldofwonder.init.WonderPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class DandelionFieldsBiomeDecorator {

	@SuppressWarnings("unused")
	private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return biome(precipitation, temperature, downfall, spawnBuilder, biomeBuilder);
	}

	private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return (new Biome.BiomeBuilder())
				.hasPrecipitation(hasPrecipitation)
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(4159204)
						.waterFogColor(329011)
						.fogColor(12638463)
						.skyColor(7907327)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.mobSpawnSettings(spawnBuilder.build())
				.generationSettings(biomeBuilder.build())
				.build();
	}

	public static Biome decorateDandelionFields(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder biomeFeatures = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
		globalOverworldGeneration(biomeFeatures);
		BiomeDefaultFeatures.addPlainGrass(biomeFeatures);
		BiomeDefaultFeatures.addDefaultOres(biomeFeatures);
		BiomeDefaultFeatures.addDefaultSoftDisks(biomeFeatures);
		BiomeDefaultFeatures.addDefaultFlowers(biomeFeatures);
		BiomeDefaultFeatures.addDefaultMushrooms(biomeFeatures);
		BiomeDefaultFeatures.addDefaultExtraVegetation(biomeFeatures);
		addDandelionFieldsVegetation(biomeFeatures);
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
		spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WonderEntities.DANDE_LION.get(), 30, 2, 3));
		spawnSettings.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
		return biome(true, 0.7F, 0.8F, spawnSettings, biomeFeatures);
	}
	
	private static void globalOverworldGeneration(BiomeGenerationSettings.Builder p_194870_) {
	      BiomeDefaultFeatures.addDefaultCarversAndLakes(p_194870_);
	      BiomeDefaultFeatures.addDefaultCrystalFormations(p_194870_);
	      BiomeDefaultFeatures.addDefaultMonsterRoom(p_194870_);
	      BiomeDefaultFeatures.addDefaultUndergroundVariety(p_194870_);
	      BiomeDefaultFeatures.addDefaultSprings(p_194870_);
	      BiomeDefaultFeatures.addSurfaceFreezing(p_194870_);
	   }
	
	//generationSettings.addStructureStart(Structures.VILLAGE_PLAINS).addStructureStart(Structures.PILLAGER_OUTPOST);

	public static void addDandelionFieldsVegetation(BiomeGenerationSettings.Builder builder) {
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WonderPlacedFeatures.PLACED_DANDELION_FLOWERS);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WonderPlacedFeatures.PLACED_DANDELION_FLUFF);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WonderPlacedFeatures.PLACED_DANDELION);
	}

}
