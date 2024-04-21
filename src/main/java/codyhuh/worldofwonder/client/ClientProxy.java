package codyhuh.worldofwonder.client;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.client.renderer.item.DandelionHatRenderProperties;
import codyhuh.worldofwonder.common.CommonProxy;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public Object getArmorRenderProperties() {
		return new DandelionHatRenderProperties();
	}
	
}
