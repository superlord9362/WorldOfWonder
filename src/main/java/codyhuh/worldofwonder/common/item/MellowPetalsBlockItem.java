package codyhuh.worldofwonder.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class MellowPetalsBlockItem extends BlockItem {
    public MellowPetalsBlockItem(Block p_220226_, Item.Properties p_220227_) {
        super(p_220226_, p_220227_);
    }

    public InteractionResult useOn(UseOnContext p_220229_) {
        return InteractionResult.PASS;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult fluidHit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        InteractionResult interactionresult = null;
        if (level.getFluidState(fluidHit.getBlockPos()).is(Fluids.WATER)) {
            BlockHitResult aboveFluidHit = fluidHit.withPosition(fluidHit.getBlockPos().above());
            interactionresult = super.useOn(new UseOnContext(player, hand, aboveFluidHit));
            return new InteractionResultHolder<>(interactionresult, player.getItemInHand(hand));
        } else {
            BlockHitResult blockhitresult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
//            System.out.println("Result was not water. Printing blockstate...");
//            System.out.println(level.getBlockState(blockhitresult.getBlockPos()));
            interactionresult = super.useOn(new UseOnContext(player, hand, blockhitresult));
            return new InteractionResultHolder<>(interactionresult, player.getItemInHand(hand));
        }
    }
}
