package codyhuh.worldofwonder.common.block.trees;

import codyhuh.worldofwonder.core.registry.WonderFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DandelionFluffTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return WonderFeatures.WonderConfiguredFeatures.DANDELION_FLUFF;
    }
}
