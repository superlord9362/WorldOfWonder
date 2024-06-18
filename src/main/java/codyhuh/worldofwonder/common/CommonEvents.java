package codyhuh.worldofwonder.common;

import com.google.common.collect.ImmutableMap;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.core.WonderBlocks;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
	
	@SubscribeEvent
	public static void init(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
					.put(WonderBlocks.STEM_LOG.get(), WonderBlocks.STRIPPED_STEM_LOG.get())
					.put(WonderBlocks.STEM_WOOD.get(), WonderBlocks.STRIPPED_STEM_WOOD.get()).build();
		});
	}

}
