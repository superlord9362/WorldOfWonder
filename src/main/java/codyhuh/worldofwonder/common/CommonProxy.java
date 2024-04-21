package codyhuh.worldofwonder.common;

import codyhuh.worldofwonder.WorldOfWonder;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID)
public class CommonProxy {

	public Object getArmorRenderProperties() {
        return null;
    }
	
}
