package codyhuh.worldofwonder.common.block;

import javax.annotation.Nullable;

import codyhuh.worldofwonder.core.registry.WonderBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class PottedSproutBlock extends FlowerPotBlock {
	private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	public PottedSproutBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WonderBlocks.DANDE_LION_SPROUT, Block.Properties.of().noOcclusion());
		registerDefaultState(defaultBlockState().setValue(AXIS, Direction.Axis.X));
	}

	@Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (placer != null) worldIn.setBlock(pos, state.setValue(AXIS, placer.getDirection().getOpposite().getAxis()), 3);
    }

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getNearestLookingDirection();
		return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, direction.getOpposite().getAxis());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}
}
