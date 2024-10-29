package codyhuh.worldofwonder.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class MellowPetalsBlock extends PinkPetalsBlock {
    public static final BooleanProperty WATERBORNE;

    public MellowPetalsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERBORNE, false).setValue(FACING, Direction.NORTH).setValue(AMOUNT, Integer.valueOf(1)));
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos);
        FluidState fluidstate1 = level.getFluidState(pos.above());
        boolean waterborne = fluidstate.getType() == Fluids.WATER && fluidstate1.getType() == Fluids.EMPTY;
        return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) || waterborne;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
//        System.out.println("State below is: " + );
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos().below());
        if (fluid.isSource() && fluid.is(FluidTags.WATER)) {
            return blockstate.is(this) ? blockstate.setValue(WATERBORNE, true).setValue(AMOUNT, Integer.valueOf(Math.min(4, blockstate.getValue(AMOUNT) + 1))) : this.defaultBlockState().setValue(WATERBORNE, true).setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return blockstate.is(this) ? blockstate.setValue(AMOUNT, Integer.valueOf(Math.min(4, blockstate.getValue(AMOUNT) + 1))) : this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AMOUNT, WATERBORNE);
    }

    static {
        WATERBORNE = BooleanProperty.create("waterborne");
    }
}
