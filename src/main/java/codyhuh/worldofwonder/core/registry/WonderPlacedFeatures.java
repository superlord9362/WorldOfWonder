package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class WonderPlacedFeatures {

	public static void init() { }

	public static final ResourceKey<PlacedFeature> PLACED_DANDELION = registerPlacedFeature("placed_dandelion");
	public static final ResourceKey<PlacedFeature> PLACED_DANDELION_FLUFF = registerPlacedFeature("placed_dandelion_fluff");
	public static final ResourceKey<PlacedFeature> PLACED_DANDELION_FLOWERS = registerPlacedFeature("placed_dandelion_flowers");

	public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
		HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
		PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
		PlacementUtils.register(bootstapContext, PLACED_DANDELION_FLOWERS, holderGetter.getOrThrow(WonderFeatures.DANDELION_FLOWERS), NoiseThresholdCountPlacement.of(-0.8D, 15, 4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(bootstapContext, PLACED_DANDELION, holderGetter.getOrThrow(WonderFeatures.DANDELION), PlacementUtils.countExtra(1, 0.05F, 1), InSquarePlacement.spread(), placementmodifier, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
		PlacementUtils.register(bootstapContext, PLACED_DANDELION_FLUFF, holderGetter.getOrThrow(WonderFeatures.DANDELION_FLUFF), PlacementUtils.countExtra(1, 0.05F, 1), InSquarePlacement.spread(), placementmodifier, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
	}

	public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WorldOfWonder.MOD_ID, id));
	}
}
