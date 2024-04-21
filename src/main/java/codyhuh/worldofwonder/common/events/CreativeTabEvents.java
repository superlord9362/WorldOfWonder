package codyhuh.worldofwonder.common.events;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.compat.WonderQuarkCompat;
import codyhuh.worldofwonder.init.WonderBlocks;
import codyhuh.worldofwonder.init.WonderItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabEvents {

	@SubscribeEvent
	public static void buildContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(WonderBlocks.STEM_PLANKS.get().asItem());
			event.accept(WonderBlocks.STEM_LOG.get().asItem());
			event.accept(WonderBlocks.STRIPPED_STEM_LOG.get().asItem());
			event.accept(WonderBlocks.STEM_WOOD.get().asItem());
			event.accept(WonderBlocks.STRIPPED_STEM_WOOD.get().asItem());
			event.accept(WonderBlocks.STEM_STAIRS.get().asItem());
			event.accept(WonderBlocks.STEM_SLAB.get().asItem());
			event.accept(WonderBlocks.STEM_FENCE.get().asItem());
			event.accept(WonderBlocks.STEM_FENCE_GATE.get().asItem());
			event.accept(WonderBlocks.STEM_DOOR.get().asItem());
			event.accept(WonderBlocks.STEM_TRAPDOOR.get().asItem());
			event.accept(WonderBlocks.STEM_PRESSURE_PLATE.get().asItem());
			event.accept(WonderBlocks.STEM_BUTTON.get().asItem());
		}
		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			event.accept(WonderBlocks.STEM_LOG.get().asItem());
			event.accept(WonderBlocks.DANDELION_PETALS.get().asItem());
			event.accept(WonderBlocks.DANDE_LION_SPROUT.get().asItem());
			event.accept(WonderBlocks.DANDELION_FLUFF.get().asItem());
		}
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			event.accept(WonderBlocks.STEM_SIGN.get().asItem());
			event.accept(WonderBlocks.STEM_HANGING_SIGN.get().asItem());
		}
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(WonderItems.BLOOM_MEAL.get());
		}
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(WonderItems.STEM_BOAT.get());
			event.accept(WonderItems.STEM_CHEST_BOAT.get());
		}
		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			event.accept(WonderItems.DANDELION_HAT.get());
		}
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(WonderItems.DANDELION_SPAWN_EGG.get());
		}
		if (ModList.get().isLoaded("quark")) {
			if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
				event.accept(WonderQuarkCompat.STEM_CHEST_BLOCK.get().asItem());
				event.accept(WonderQuarkCompat.STEM_TRAPPED_CHEST_BLOCK.get().asItem());
			}
			if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
				event.accept(WonderQuarkCompat.STEM_CHEST_BLOCK.get().asItem());
				event.accept(WonderQuarkCompat.STEM_LADDER.get().asItem());
				event.accept(WonderQuarkCompat.STEM_BOOKSHELF.get().asItem());
			}
			if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
				event.accept(WonderQuarkCompat.STEM_VERTICAL_SLAB.get().asItem());
			}
		}
	}
	
}
