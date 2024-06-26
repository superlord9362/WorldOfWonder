package codyhuh.worldofwonder.common.block;

import codyhuh.worldofwonder.common.tileentity.WonderHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WonderWallHangingSignBlock extends WallHangingSignBlock {

	public WonderWallHangingSignBlock(Properties p_251606_, WoodType p_252140_) {
		super(p_251606_, p_252140_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_250745_, BlockState p_250905_) {
		return new WonderHangingSignBlockEntity(p_250745_, p_250905_);
	}

}
