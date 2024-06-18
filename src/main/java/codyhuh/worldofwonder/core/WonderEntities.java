package codyhuh.worldofwonder.core;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import codyhuh.worldofwonder.common.entity.DandeLionSeedEntity;
import codyhuh.worldofwonder.common.entity.StemBoatEntity;
import codyhuh.worldofwonder.common.entity.StemChestBoatEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WonderEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WorldOfWonder.MOD_ID);
    public static final RegistryObject<EntityType<DandeLionEntity>> DANDE_LION = add("dande_lion", DandeLionEntity::new, MobCategory.CREATURE, 0.8f, 1.1f);
    public static final RegistryObject<EntityType<StemBoatEntity>> STEM_BOAT = add("stem_boat", StemBoatEntity::new, MobCategory.MISC, 1.375f, 0.5625f);
    public static final RegistryObject<EntityType<StemChestBoatEntity>> STEM_CHEST_BOAT = add("stem_chest_boat", StemChestBoatEntity::new, MobCategory.MISC, 1.375f, 0.5625f);
    public static final RegistryObject<EntityType<DandeLionSeedEntity>> DANDE_LION_SEED = add("dande_lion_seed", DandeLionSeedEntity::new, MobCategory.MISC, 1f, 1f);

    public static void setupAttributes(EntityAttributeCreationEvent event) {
        event.put(DANDE_LION.get(), DandeLionEntity.registerAttributes().build());
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> add(String name, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height) {
        return REGISTRY.register(name, () -> create(name, factory, classification, width, height));
    }

    private static <T extends Entity> EntityType<T> create(String name, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height) {
        return EntityType.Builder.of(factory, classification).sized(width, height).build(name);
    }
}
