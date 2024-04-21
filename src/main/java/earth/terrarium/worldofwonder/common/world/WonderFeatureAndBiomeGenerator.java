package earth.terrarium.worldofwonder.common.world;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import earth.terrarium.worldofwonder.WorldOfWonder;
import earth.terrarium.worldofwonder.init.WonderBiomes;
import earth.terrarium.worldofwonder.init.WonderFeatures;
import earth.terrarium.worldofwonder.init.WonderPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class WonderFeatureAndBiomeGenerator extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ctx -> WonderFeatures.bootstrap(ctx))
            .add(Registries.PLACED_FEATURE, WonderPlacedFeatures::bootstrap)
            .add(Registries.BIOME, WonderBiomes::bootstrap);

    public WonderFeatureAndBiomeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(WorldOfWonder.MOD_ID));
    }

}

