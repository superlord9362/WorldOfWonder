package codyhuh.worldofwonder.common.events;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.block.trees.DandelionFluffTree;
import codyhuh.worldofwonder.common.block.trees.DandelionTree;
import codyhuh.worldofwonder.core.WonderBlocks;
import codyhuh.worldofwonder.core.WonderItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = WorldOfWonder.MOD_ID)
public class ItemEvents {
    public static final DispenseItemBehavior BLOOM_MEAL_DISPENSE = new BloomMealDispenseBehavior();
    private static final AbstractTreeGrower DANDELION_TREE = new DandelionTree();
    private static final AbstractTreeGrower FLUFF_TREE = new DandelionFluffTree();

    @SubscribeEvent
    public static void interact(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() == Blocks.FLOWER_POT && stack.getItem() == WonderBlocks.DANDE_LION_SPROUT.get().asItem()) {
			level.setBlock(pos, WonderBlocks.POTTED_DANDE_LION_SPROUT.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, event.getEntity().getDirection().getOpposite().getAxis()), 0);
			event.getEntity().awardStat(Stats.POT_FLOWER);
			if (!event.getEntity().getAbilities().instabuild) {
				stack.shrink(1);
			}
			event.setUseBlock(Event.Result.DENY);
			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
        handleBloomMeal(level, stack, pos, event.getEntity(), event.getHand());
    }

    public static boolean handleBloomMeal(Level level, ItemStack stack, BlockPos pos, Player player, InteractionHand hand) {
        BlockState state = level.getBlockState(pos);
        if (stack.getItem() == WonderItems.BLOOM_MEAL.get() && state.getBlock() == Blocks.DANDELION) {
            if (!level.isClientSide) {
                RandomSource random = level.random;
                level.levelEvent(2005, pos, 0);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                if (random.nextInt(3) == 0) {
                    (random.nextInt(4) == 0 ? FLUFF_TREE : DANDELION_TREE).growTree((ServerLevel) level, ((ServerLevel) level).getChunkSource().getGenerator(), pos, state, random);
                }
                return true;
            } else {
                if (hand != null) {
                    player.swing(hand);
                }
            }
        }
        return false;
    }

    private static class BloomMealDispenseBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource source, ItemStack stack) {
            this.setSuccess(true);
            ServerLevel level = source.getLevel();
            BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
            if (!level.isClientSide) {
                if (!handleBloomMeal(level, stack, blockpos, FakePlayerFactory.getMinecraft(level), null)) {
                    this.setSuccess(false);
                } else {
                    level.levelEvent(2005, blockpos, 0);
                }
            }

            return stack;
        }
    }
}
