package codyhuh.worldofwonder.common.tileentity;

import java.util.Objects;

import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import codyhuh.worldofwonder.core.WonderEntities;
import codyhuh.worldofwonder.core.WonderTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DandeLionSproutTileEntity extends BlockEntity  {
    public int age = 1;

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

	public static void tick(Level world, BlockPos pos, BlockState state, DandeLionSproutTileEntity sprout) {
        if (sprout.hasLevel() && !Objects.requireNonNull(world).isClientSide) {
//                ++blockEntity.age >= (blockEntity.maxAge == -1 ? blockEntity.maxAge = 48000 + world.random.nextInt(12000) : blockEntity.maxAge)) {
            if (sprout.age > 0) {
                sprout.age = -1 * (48000 + world.random.nextInt(12000));
            }
            if (sprout.age == 0) {
                DandeLionEntity entity = WonderEntities.DANDE_LION.get().create(world);
                if (entity != null && sprout.level != null) {
                    entity.setAge(-24000);
                    entity.setPos(sprout.getBlockPos().getX() + 0.5, sprout.getBlockPos().getY(), sprout.getBlockPos().getZ() + 0.5);
                    sprout.level.addFreshEntity(entity);
                    sprout.level.destroyBlock(sprout.getBlockPos(), false);
                }
            }
            sprout.age++;
        }
    }
}
