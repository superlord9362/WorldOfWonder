package codyhuh.worldofwonder.common.compat;

import java.util.function.Supplier;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.block.StemChestBlock;
import codyhuh.worldofwonder.common.block.StemTrappedChestBlock;
import codyhuh.worldofwonder.common.block.VerticalSlabBlock;
import codyhuh.worldofwonder.common.block.WonderBookshelfBlock;
import codyhuh.worldofwonder.common.tileentity.StemChestTileEntity;
import codyhuh.worldofwonder.common.tileentity.StemTrappedChestTileEntity;
import codyhuh.worldofwonder.common.util.BlockEntitySubRegistryHelper;
import codyhuh.worldofwonder.common.util.BlockSubRegistryHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WonderQuarkCompat {
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldOfWonder.MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, WorldOfWonder.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> ENTITY_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WorldOfWonder.MOD_ID);
	public static final BlockEntitySubRegistryHelper ENTITY_HELPER = WorldOfWonder.REGISTRY_HELPER.getBlockEntitySubHelper();
	private static final BlockSubRegistryHelper BLOCK_HELPER = WorldOfWonder.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> STEM_LADDER = add("stem_ladder", () -> new LadderBlock(Block.Properties.of().strength(0.4F).noOcclusion().sound(SoundType.LADDER)));
    public static final RegistryObject<Block> STEM_BOOKSHELF = add("stem_bookshelf", () -> new WonderBookshelfBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STEM_VERTICAL_SLAB = add("stem_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    
    public static final RegistryObject<StemChestBlock> STEM_CHEST_BLOCK = BLOCK_HELPER.createChestBlock("stem", Block.Properties.copy(Blocks.CHEST));
    @SuppressWarnings("deprecation")
	public static final RegistryObject<StemTrappedChestBlock> STEM_TRAPPED_CHEST_BLOCK = BLOCK_HELPER.createTrappedChestBlock("stem", Block.Properties.copy(Blocks.TRAPPED_CHEST));
    public static final RegistryObject<BlockEntityType<StemChestTileEntity>> STEM_CHEST = ENTITY_HELPER.createBlockEntity("chest", StemChestTileEntity::new, StemChestBlock.class);
	public static final RegistryObject<BlockEntityType<StemTrappedChestTileEntity>> STEM_TRAPPED_CHEST = ENTITY_HELPER.createBlockEntity("trapped_chest", StemTrappedChestTileEntity::new, StemTrappedChestBlock.class);

    public static <T extends Block> RegistryObject<T> add(String name, Supplier<T> block) {
        final RegistryObject<T> registryObject = BLOCK_REGISTER.register(name, block);
        ITEM_REGISTER.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties()));
        return registryObject;
    }
}
