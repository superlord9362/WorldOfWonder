package codyhuh.worldofwonder.core.data.server;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.core.registry.WonderBiomes;
import codyhuh.worldofwonder.core.registry.WonderFeatures;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WonderDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, WonderFeatures.WonderConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, WonderFeatures.WonderPlacedFeatures::bootstrap)
            .add(Registries.BIOME, WonderBiomes::bootstrap)
            .add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, WonderBiomeSlices::bootstrap);

    public WonderDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(WorldOfWonder.MOD_ID));
    }

}

