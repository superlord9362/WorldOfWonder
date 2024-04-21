package codyhuh.worldofwonder.common.block;

import codyhuh.worldofwonder.common.tileentity.StemSignTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WonderWallSignBlock extends WallSignBlock {
	
	public WonderWallSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StemSignTileEntity(pos, state);
	}

}
