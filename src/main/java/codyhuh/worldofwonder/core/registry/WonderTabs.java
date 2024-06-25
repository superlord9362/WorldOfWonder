package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WonderTabs {
	
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WorldOfWonder.MOD_ID);

	public static final RegistryObject<CreativeModeTab> ITEM_GROUP = REGISTRY.register("world_of_wonder_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(WonderItems.BLOOM_MEAL.get()))
			.title(Component.translatable("itemGroup.world_of_wonder_item_group"))
			.displayItems((pParameters, pOutput) -> {
			}).build());

}
