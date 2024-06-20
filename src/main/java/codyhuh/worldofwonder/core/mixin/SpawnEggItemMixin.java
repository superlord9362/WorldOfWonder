package codyhuh.worldofwonder.core.mixin;

import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SpawnEggItem.class)
public class SpawnEggItemMixin {
    @Inject(method = "spawnOffspringFromSpawnEgg", at = @At("HEAD"), cancellable = true)
    private void lionEgg(Player player, Mob mob, EntityType<? extends Mob> type, ServerLevel level, Vec3 vec3, ItemStack stack, CallbackInfoReturnable<Optional<Mob>> cir) {
        if (mob instanceof DandeLionEntity) {
            cir.setReturnValue(Optional.empty());
        }
    }
}
