package earth.terrarium.worldofwonder.common.block;

import earth.terrarium.worldofwonder.common.compat.IChestBlock;
import earth.terrarium.worldofwonder.common.compat.WonderQuarkCompat;
import earth.terrarium.worldofwonder.common.tileentity.StemChestTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StemChestBlock extends ChestBlock implements IChestBlock {
	public final String type;

	public StemChestBlock(String type, Properties props) {
		super(props, WonderQuarkCompat.STEM_CHEST::get);
		this.type = type;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StemChestTileEntity(pos, state);
	}

	@Override
	public String getChestMaterialsName() {
		return type;
	}
}