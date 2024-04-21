package codyhuh.worldofwonder.common.block;

import codyhuh.worldofwonder.common.tileentity.WonderHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WonderCeilingHangingSignBlock extends CeilingHangingSignBlock {

	public WonderCeilingHangingSignBlock(Properties p_250481_, WoodType p_248716_) {
		super(p_250481_, p_248716_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_250745_, BlockState p_250905_) {
		return new WonderHangingSignBlockEntity(p_250745_, p_250905_);
	}

}
