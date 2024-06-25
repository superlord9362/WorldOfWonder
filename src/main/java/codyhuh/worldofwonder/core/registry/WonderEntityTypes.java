package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import codyhuh.worldofwonder.common.entity.DandeLionSeedEntity;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WonderEntityTypes {
    public static final EntitySubRegistryHelper HELPER = WorldOfWonder.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<DandeLionEntity>> DANDE_LION = HELPER.createLivingEntity("dande_lion", DandeLionEntity::new, MobCategory.CREATURE, 0.8f, 1.1f);
    public static final RegistryObject<EntityType<DandeLionSeedEntity>> DANDE_LION_SEED = HELPER.createEntity("dande_lion_seed", DandeLionSeedEntity::new, DandeLionSeedEntity::new, MobCategory.MISC, 1f, 1f);


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(DANDE_LION.get(), DandeLionEntity.registerAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(DANDE_LION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }
}
