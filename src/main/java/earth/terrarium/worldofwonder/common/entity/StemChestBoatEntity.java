package earth.terrarium.worldofwonder.common.entity;

import earth.terrarium.worldofwonder.common.entity.StemBoatEntity.WonderBoatTypes;
import earth.terrarium.worldofwonder.init.WonderEntities;
import earth.terrarium.worldofwonder.init.WonderItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class StemChestBoatEntity  extends ChestBoat {

	private static final EntityDataAccessor<Integer> CHEST_BOAT_TYPE = SynchedEntityData.defineId(StemChestBoatEntity.class, EntityDataSerializers.INT);

	public StemChestBoatEntity(EntityType<? extends Entity> entityType, Level world) {
		super(WonderEntities.STEM_CHEST_BOAT.get(), world);
	}

	public StemChestBoatEntity(Level world, double positionX, double positionY, double positionZ) {
		super(WonderEntities.STEM_CHEST_BOAT.get(), world);
		this.setPos(positionX, positionY, positionZ);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = positionX;
		this.yo = positionY;
		this.zo = positionZ;
	}

	public StemChestBoatEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(WonderEntities.STEM_CHEST_BOAT.get(), world);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("WoodType", this.getWonderChestBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("WoodType", 8)) this.setWonderChestBoatType(WonderBoatTypes.byName(compound.getString("WoodType")));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CHEST_BOAT_TYPE, WonderBoatTypes.STEM.ordinal());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setWonderChestBoatType(WonderBoatTypes type) {
		this.entityData.set(CHEST_BOAT_TYPE, type.ordinal());
	}

	public WonderBoatTypes getWonderChestBoatType() {
		return WonderBoatTypes.byId(this.entityData.get(CHEST_BOAT_TYPE));
	}

	@Override
	public Boat.Type getVariant() {
		return Boat.Type.OAK;
	}

	@Override
	public void setVariant(Boat.Type boatType) {
	}

	@Override
	public Item getDropItem() {
		switch(this.getWonderChestBoatType()) {
		case STEM:
		default:
			return WonderItems.STEM_CHEST_BOAT.get();
		}
	}

}