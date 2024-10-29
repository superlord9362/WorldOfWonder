package codyhuh.worldofwonder.core.registry;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.block.entity.DandeLionSproutTileEntity;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WonderBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper HELPER = WorldOfWonder.REGISTRY_HELPER.getBlockEntitySubHelper();
//    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WorldOfWonder.MOD_ID);
//    public static final RegistryObject<BlockEntityType<DandeLionSproutTileEntity>> DANDE_LION_SPROUT = REGISTRY.register("dande_lion_sprout", () -> BlockEntityType.Builder.of(DandeLionSproutTileEntity::new, WonderBlocks.DANDE_LION_SPROUT.get()).build(null));
    public static final RegistryObject<BlockEntityType<DandeLionSproutTileEntity>> DANDE_LION_SPROUT =
            HELPER.createBlockEntity("dande_lion_sprout", DandeLionSproutTileEntity::new, () -> Set.of(
                    WonderBlocks.DANDE_LION_SPROUT.get()
            ));
}
