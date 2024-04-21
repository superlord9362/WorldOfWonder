package earth.terrarium.worldofwonder.common.tileentity;

import earth.terrarium.worldofwonder.common.compat.WonderQuarkCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class StemTrappedChestTileEntity extends StemChestTileEntity {
   public StemTrappedChestTileEntity(BlockPos pos, BlockState state) {
      super(WonderQuarkCompat.STEM_TRAPPED_CHEST.get(), pos, state);
   }

    @Override
    public boolean isTrapped() {
        return true;
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        this.level.updateNeighborsAt(pos.below(), state.getBlock());
   }
}
