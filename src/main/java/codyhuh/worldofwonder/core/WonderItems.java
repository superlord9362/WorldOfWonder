package codyhuh.worldofwonder.core;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.entity.StemBoatEntity.WonderBoatTypes;
import codyhuh.worldofwonder.common.item.ItemDandelionHat;
import codyhuh.worldofwonder.common.item.StemBoatItem;
import codyhuh.worldofwonder.common.item.StemChestBoatItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WonderItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, WorldOfWonder.MOD_ID);
    public static final RegistryObject<Item> STEM_BOAT = REGISTRY.register("stem_boat", StemBoatItem::new);
    public static final RegistryObject<Item> STEM_CHEST_BOAT = REGISTRY.register("stem_chest_boat", () -> new StemChestBoatItem(WonderBoatTypes.STEM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DANDELION_HAT = REGISTRY.register("dandelion_hat", ItemDandelionHat::new);
    public static final RegistryObject<Item> BLOOM_MEAL = REGISTRY.register("bloom_meal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DANDELION_SPAWN_EGG = REGISTRY.register("dande_lion_spawn_egg", () -> new ForgeSpawnEggItem(WonderEntities.DANDE_LION, 0x79ae45, 0xf9df55, new Item.Properties()));
}
