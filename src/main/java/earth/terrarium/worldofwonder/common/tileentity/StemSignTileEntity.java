package earth.terrarium.worldofwonder.common.tileentity;

import earth.terrarium.worldofwonder.init.WonderTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StemSignTileEntity extends SignBlockEntity {
    public StemSignTileEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return WonderTileEntities.STEM_SIGN.get();
    }

}