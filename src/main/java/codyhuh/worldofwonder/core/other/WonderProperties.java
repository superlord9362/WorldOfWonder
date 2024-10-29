package codyhuh.worldofwonder.core.other;

import codyhuh.worldofwonder.WorldOfWonder;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class WonderProperties {
    public static final BlockSetType STEM_BLOCK_SET = blockSetType("stem");
    public static final PropertyUtil.WoodSetProperties STEM = PropertyUtil.WoodSetProperties.builder(MapColor.COLOR_LIGHT_GREEN).build();

    public static final WoodType STEM_WOOD_TYPE = woodSetType(STEM_BLOCK_SET);

    public static BlockSetType blockSetType(String name) {
        return BlockSetTypeRegistryHelper.register(new BlockSetType(WorldOfWonder.MOD_ID + ":" + name));
    }

    public static WoodType woodSetType(BlockSetType type) {
        return WoodTypeRegistryHelper.registerWoodType(new WoodType(type.name(), type));
    }
}
