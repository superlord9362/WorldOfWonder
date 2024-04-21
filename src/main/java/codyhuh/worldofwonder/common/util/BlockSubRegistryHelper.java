package codyhuh.worldofwonder.common.util;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import codyhuh.worldofwonder.client.renderer.tileentity.StemChestTileEntityWithoutLevelRenderer;
import codyhuh.worldofwonder.client.util.ChestManager;
import codyhuh.worldofwonder.common.block.StemChestBlock;
import codyhuh.worldofwonder.common.block.StemTrappedChestBlock;
import codyhuh.worldofwonder.common.tileentity.StemChestTileEntity;
import codyhuh.worldofwonder.common.tileentity.StemTrappedChestTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockSubRegistryHelper extends AbstractSubRegistryHelper<Block> {
	protected final DeferredRegister<Item> itemRegister;

	public BlockSubRegistryHelper(RegistryHelper parent) {
		this(parent, parent.getSubHelper(ForgeRegistries.ITEMS).getDeferredRegister(), DeferredRegister.create(ForgeRegistries.BLOCKS, parent.getModId()));
	}

	public BlockSubRegistryHelper(RegistryHelper parent, ISubRegistryHelper<Item> itemHelper) {
		this(parent, itemHelper.getDeferredRegister(), DeferredRegister.create(ForgeRegistries.BLOCKS, parent.getModId()));
	}

	public BlockSubRegistryHelper(RegistryHelper parent, DeferredRegister<Item> itemRegister, DeferredRegister<Block> deferredRegister) {
		super(parent, deferredRegister);
		this.itemRegister = itemRegister;
	}

	@OnlyIn(Dist.CLIENT)
	private static BEWLRBlockItem.LazyBEWLR chestBEWLR(boolean trapped) {
		return trapped ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> {
			return new StemChestTileEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new StemTrappedChestTileEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState()));
		}) : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> {
			return new StemChestTileEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new StemChestTileEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState()));
		});
	}

	public <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier) {
		RegistryObject<B> block = this.deferredRegister.register(name, supplier);
		this.itemRegister.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
		return block;
	}

	public <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, Item.Properties properties) {
		RegistryObject<B> block = this.deferredRegister.register(name, supplier);
		this.itemRegister.register(name, () -> new BlockItem(block.get(), properties));
		return block;
	}

	public <B extends Block> RegistryObject<B> createBlockWithItem(String name, Supplier<? extends B> supplier, Supplier<BlockItem> item) {
		this.itemRegister.register(name, item);
		return this.deferredRegister.register(name, supplier);
	}

	public <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
		return this.deferredRegister.register(name, supplier);
	}

	public <B extends Block> RegistryObject<B> createBlockWithBEWLR(String name, Supplier<? extends B> supplier, Supplier<Callable<BEWLRBlockItem.LazyBEWLR>> belwr) {
		RegistryObject<B> block = this.deferredRegister.register(name, supplier);
		this.itemRegister.register(name, () -> new BEWLRBlockItem(block.get(), new Item.Properties(), belwr));
		return block;
	}


	public RegistryObject<StemChestBlock> createChestBlock(String name, String materialName, Block.Properties properties) {
		String modId = this.parent.getModId();
		String chestMaterialsName = ChestManager.registerMaterials(modId, materialName, false);
		RegistryObject<StemChestBlock> block = this.deferredRegister.register(name, () -> new StemChestBlock(chestMaterialsName, properties));
		this.itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(false), 300));
		return block;
	}

	public RegistryObject<StemChestBlock> createChestBlock(String materialName, Block.Properties properties) {
		return createChestBlock(materialName + "_chest", materialName, properties);
	}

	@SuppressWarnings("unused")
	public RegistryObject<StemTrappedChestBlock> createTrappedChestBlock(String name, String materialName, Block.Properties properties) {
		String modId = this.parent.getModId();
		RegistryObject<StemTrappedChestBlock> block = this.deferredRegister.register(name, () -> new StemTrappedChestBlock(modId + ":" + materialName + "_trapped", properties));
		String chestMaterialsName = ChestManager.registerMaterials(modId, materialName, true);
		this.itemRegister.register(name, () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true), 300));
		return block;
	}

	@Deprecated
	public RegistryObject<StemTrappedChestBlock> createTrappedChestBlock(String materialName, Block.Properties properties) {
		return createTrappedChestBlock(materialName + "_trapped_chest", materialName, properties);
	}

	public RegistryObject<StemTrappedChestBlock> createTrappedChestBlockNamed(String materialName, Block.Properties properties) {
		return createTrappedChestBlock("trapped_" + materialName + "_chest", materialName, properties);
	}

}