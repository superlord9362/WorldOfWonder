package codyhuh.worldofwonder.init;

import codyhuh.worldofwonder.WorldOfWonder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WonderWoodTypes {

	public static final BlockSetType STEM_TYPE = BlockSetType.register(new BlockSetType(new ResourceLocation(WorldOfWonder.MOD_ID, "stem").toString()));

	public static WoodType STEM = WoodType.register(new WoodType(new ResourceLocation(WorldOfWonder.MOD_ID, "stem").toString(), STEM_TYPE));
	
}
