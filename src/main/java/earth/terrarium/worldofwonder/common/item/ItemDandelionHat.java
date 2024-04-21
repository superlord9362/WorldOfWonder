package earth.terrarium.worldofwonder.common.item;

import javax.annotation.Nullable;

import earth.terrarium.worldofwonder.WorldOfWonder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemDandelionHat extends ArmorItem {
    public static final ArmorMaterial MATERIAL = new WonderArmorMaterial(WorldOfWonder.MOD_ID + ":dandelion", 1, new int[]{1, 2, 3, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0, () -> Ingredient.of(WorldOfWonder.DANDELION));

    public ItemDandelionHat() {
        super(MATERIAL, ArmorItem.Type.HELMET, new Item.Properties());
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isAlive() && player.onGround()) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 130, 0, false, false, true));
        }
    }
    
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept((IClientItemExtensions) WorldOfWonder.PROXY.getArmorRenderProperties());
	}

    @Override
	@Nullable
	@OnlyIn(Dist.CLIENT) 
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "worldofwonder:textures/models/armor/dandelion_fuzz_hat.png";
	}
}
