package codyhuh.worldofwonder.common.block.trees;

import javax.annotation.Nullable;

import codyhuh.worldofwonder.core.registry.WonderFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class DandelionFluffTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return WonderFeatures.DANDELION_FLUFF;
    }
}
