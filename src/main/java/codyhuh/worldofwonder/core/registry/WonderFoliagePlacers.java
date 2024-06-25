package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.world.gen.foliage.DandelionFluffFoliagePlacer;
import codyhuh.worldofwonder.common.world.gen.foliage.DandelionFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WonderFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, WorldOfWonder.MOD_ID);
    public static final RegistryObject<FoliagePlacerType<DandelionFoliagePlacer>> DANDELION = REGISTRY.register("dandelion", () -> new FoliagePlacerType<>(DandelionFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<DandelionFluffFoliagePlacer>> DANDELION_FLUFF = REGISTRY.register("dandelion_fluff", () -> new FoliagePlacerType<>(DandelionFluffFoliagePlacer.CODEC));
}
