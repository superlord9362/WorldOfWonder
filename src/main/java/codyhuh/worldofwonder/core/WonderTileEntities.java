package codyhuh.worldofwonder.core;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.tileentity.DandeLionSproutTileEntity;
import codyhuh.worldofwonder.common.tileentity.StemSignTileEntity;
import codyhuh.worldofwonder.common.tileentity.WonderHangingSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WonderTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WorldOfWonder.MOD_ID);
    public static final RegistryObject<BlockEntityType<StemSignTileEntity>> STEM_SIGN = REGISTRY.register("stem_sign", () -> BlockEntityType.Builder.of(StemSignTileEntity::new, WonderBlocks.STEM_SIGN.get(), WonderBlocks.STEM_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<WonderHangingSignBlockEntity>> HANGING_SIGN = REGISTRY.register("hanging_sign", () -> BlockEntityType.Builder.of(WonderHangingSignBlockEntity::new, WonderBlocks.STEM_HANGING_SIGN.get(), WonderBlocks.STEM_HANGING_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<DandeLionSproutTileEntity>> DANDE_LION_SPROUT = REGISTRY.register("dande_lion_sprout", () -> BlockEntityType.Builder.of(DandeLionSproutTileEntity::new, WonderBlocks.DANDE_LION_SPROUT.get()).build(null));
}
