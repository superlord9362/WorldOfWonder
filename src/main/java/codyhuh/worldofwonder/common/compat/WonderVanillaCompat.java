package codyhuh.worldofwonder.common.compat;

import codyhuh.worldofwonder.common.events.ItemEvents;
import codyhuh.worldofwonder.core.WonderBlocks;
import codyhuh.worldofwonder.core.WonderEntities;
import codyhuh.worldofwonder.core.WonderItems;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class WonderVanillaCompat {
    @SuppressWarnings("deprecation")
	public static void init(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(WonderItems.BLOOM_MEAL.get(), ItemEvents.BLOOM_MEAL_DISPENSE);
        SpawnPlacements.register(WonderEntities.DANDE_LION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        registerCompostable(WonderBlocks.DANDELION_PETALS.get().asItem(), 0.3F);
        registerCompostable(WonderBlocks.DANDELION_FLUFF.get().asItem(), 0.3F);
    }

    public static void registerCompostable(ItemLike itemIn, float chance) {
        ComposterBlock.COMPOSTABLES.put(itemIn, chance);
    }
}
