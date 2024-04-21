package earth.terrarium.worldofwonder.common.util;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemSubRegistryHelper extends AbstractSubRegistryHelper<Item> {

	public ItemSubRegistryHelper(RegistryHelper parent, DeferredRegister<Item> deferredRegister) {
		super(parent, deferredRegister);
	}

	public ItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, DeferredRegister.create(ForgeRegistries.ITEMS, parent.getModId()));
	}
	public static BlockItem createSimpleBlockItem(Block blockForInput) {
		return new BlockItem(blockForInput, new Item.Properties());
	}

	public static Item.Properties createSimpleItemProperty(int stackSize) {
		return new Item.Properties().stacksTo(stackSize);
	}

	public <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		return this.deferredRegister.register(name, supplier);
	}

}