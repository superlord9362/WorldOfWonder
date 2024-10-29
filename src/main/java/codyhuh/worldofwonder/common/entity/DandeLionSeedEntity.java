package codyhuh.worldofwonder.common.entity;

import codyhuh.worldofwonder.core.WonderBlocks;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkHooks;

public class DandeLionSeedEntity extends Entity {
    private static final EntityDataAccessor<Float> X = SynchedEntityData.defineId(DandeLionSeedEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> Z = SynchedEntityData.defineId(DandeLionSeedEntity.class, EntityDataSerializers.FLOAT);

    public DandeLionSeedEntity(EntityType<? extends DandeLionSeedEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(X, 0f);
        this.entityData.define(Z, 0f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.entityData.set(X, compound.getFloat("X"));
        this.entityData.set(Z, compound.getFloat("Z"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putFloat("X", this.entityData.get(X));
        compound.putFloat("Z", this.entityData.get(Z));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setTarget(double x, double z) {
        this.entityData.set(X, (float) x);
        this.entityData.set(Z, (float) z);
    }

    @Override
    public void tick() {
        super.tick();

        if (isAlive()) {
// Current position
            Vec3 currentPos = position();

            Vec3 targetPos = new Vec3(entityData.get(X), getY(), entityData.get(Z));
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            double speed = 0.1; // Adjust the speed as necessary
            Vec3 deltaMovement = new Vec3(
                    Mth.clamp(direction.x * speed, -0.2, 0.2),
                    Math.sin(tickCount * 0.1) / 5.0,
                    Mth.clamp(direction.z * speed, -0.2, 0.2)
            );

            setDeltaMovement(deltaMovement);
            move(MoverType.SELF, getDeltaMovement());

            ParticleOptions particle = ParticleTypes.WAX_OFF;
            if (ModList.get().isLoaded("just_dandy")) {
                particle = JDParticleTypes.DANDELION_FLUFF.get();
            }
            if (tickCount % 3 == 0) {
                level().addParticle(particle, getX() + random.nextGaussian() * 0.2,
                        getEyeY() - 1.2 + random.nextGaussian() * 0.2, getZ() + random.nextGaussian() * 0.2, 0, 0, 0);
            }
            if (tickCount > 20 && level().getBlockState(blockPosition().below()).canOcclude()) {
                if (WonderBlocks.DANDE_LION_SPROUT.get().defaultBlockState().canSurvive(level(), blockPosition())
                        && level().getBlockState(blockPosition()).canBeReplaced()) {
                    for (int i = 0; i < 4; i++) {
                        level().addParticle(particle, getX() + random.nextGaussian() * 0.2,
                                getEyeY() - 1.2 + random.nextGaussian() * 0.2, getZ() + random.nextGaussian() * 0.2, 0, 0, 0);
                    }
                    level().playLocalSound(blockPosition(), SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    level().setBlock(blockPosition(), WonderBlocks.DANDE_LION_SPROUT.get().defaultBlockState(), 3);
                } else {
                    if (level() instanceof ServerLevel server) {
                        ItemStack sproutStack = WonderBlocks.DANDE_LION_SPROUT.get().asItem().getDefaultInstance();
                        sproutStack.setCount(1);
                        ItemEntity fail = new ItemEntity(level(), blockPosition().getX(), blockPosition().getY() + 0.5,
                                blockPosition().getZ(), sproutStack);
                        fail.setDeltaMovement(this.getDeltaMovement().add(new Vec3(0, 0.07, 0)));
                        server.addFreshEntity(fail);
                    }
                }
                remove(RemovalReason.DISCARDED);
            }
        }
    }
}
