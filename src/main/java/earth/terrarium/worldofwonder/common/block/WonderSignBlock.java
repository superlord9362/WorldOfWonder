package earth.terrarium.worldofwonder.common.block;

import earth.terrarium.worldofwonder.common.tileentity.StemSignTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WonderSignBlock extends StandingSignBlock {

	public WonderSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StemSignTileEntity(pos, state);
	}

}
