package codyhuh.worldofwonder.client.renderer.item;

import codyhuh.worldofwonder.client.ClientEvents;
import codyhuh.worldofwonder.client.renderer.entity.model.DandelionHatModel;
import codyhuh.worldofwonder.init.WonderItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class DandelionHatRenderProperties implements IClientItemExtensions {
	
	private static boolean init;
	
	public static DandelionHatModel DANDELION_HAT_MODEL;
	
	public static void initializeModels() {
		init = true;
		DANDELION_HAT_MODEL = new DandelionHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientEvents.DANDELION_HAT));
	}
	
	@Override
	public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
		if (!init) {
			initializeModels();
		}
		if (itemStack.getItem() == WonderItems.DANDELION_HAT.get()) {
			return DANDELION_HAT_MODEL;
		}
		return _default;
	}

}
