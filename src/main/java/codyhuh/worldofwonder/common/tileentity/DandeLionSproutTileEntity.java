package codyhuh.worldofwonder.common.tileentity;

import java.util.Objects;

import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import codyhuh.worldofwonder.init.WonderEntities;
import codyhuh.worldofwonder.init.WonderTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DandeLionSproutTileEntity extends BlockEntity  {
    private int maxAge = -1;
    private int age;

    public DandeLionSproutTileEntity(BlockPos pos, BlockState state) {
        super(WonderTileEntities.DANDE_LION_SPROUT.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        age = tag.getInt("Age");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("Age", age);
        super.saveAdditional(tag);
    }

	public static void tick(Level world, BlockPos pos, BlockState state, DandeLionSproutTileEntity blockEntity) {
        if (blockEntity.hasLevel() && !Objects.requireNonNull(world).isClientSide && ++blockEntity.age >= (blockEntity.maxAge == -1 ? blockEntity.maxAge = 48000 + world.random.nextInt(12000) : blockEntity.maxAge)) {
            DandeLionEntity entity = WonderEntities.DANDE_LION.get().create(world);
            if (entity != null) {
                entity.setAge(-24000);
                entity.setPos(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ() + 0.5);
                blockEntity.level.addFreshEntity(entity);
                blockEntity.level.destroyBlock(blockEntity.getBlockPos(), false);
            }
        }
    }
}
