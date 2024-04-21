package codyhuh.worldofwonder.init;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.world.gen.foliage.DandelionFluffFoliagePlacer;
import codyhuh.worldofwonder.common.world.gen.foliage.DandelionFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class WonderFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DANDELION = registerConfiguredFeature("dandelion");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DANDELION_FLUFF = registerConfiguredFeature("dandelion_fluff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DANDELION_FLOWERS = registerConfiguredFeature("dandelion_flowers");
    
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
    	FeatureUtils.register(bootstapContext, DANDELION, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(WonderBlocks.STEM_LOG.get().defaultBlockState()), new StraightTrunkPlacer(4, 2, 0), BlockStateProvider.simple(WonderBlocks.DANDELION_PETALS.get().defaultBlockState()), new DandelionFoliagePlacer(UniformInt.of(3, 3), UniformInt.of(0, 0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    	FeatureUtils.register(bootstapContext, DANDELION_FLUFF, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(WonderBlocks.STEM_LOG.get().defaultBlockState()), new StraightTrunkPlacer(6, 2, 0), BlockStateProvider.simple(WonderBlocks.DANDELION_FLUFF.get().defaultBlockState()), new DandelionFluffFoliagePlacer(UniformInt.of(2, 2), UniformInt.of(0, 0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    	FeatureUtils.register(bootstapContext, DANDELION_FLOWERS, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(Blocks.DANDELION), 32));
    }
    
	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
     }
    
    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WorldOfWonder.MOD_ID, id));
	}

}
