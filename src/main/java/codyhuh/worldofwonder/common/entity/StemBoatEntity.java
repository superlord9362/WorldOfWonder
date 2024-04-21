
package codyhuh.worldofwonder.common.entity;

import codyhuh.worldofwonder.init.WonderBlocks;
import codyhuh.worldofwonder.init.WonderEntities;
import codyhuh.worldofwonder.init.WonderItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class StemBoatEntity extends Boat {
	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(StemBoatEntity.class, EntityDataSerializers.INT);

	public StemBoatEntity(EntityType<? extends Entity> entityType, Level level) {
		super(WonderEntities.STEM_BOAT.get(), level);
	}

	public StemBoatEntity(Level level, double positionX, double positionY, double positionZ) {
		super(WonderEntities.STEM_BOAT.get(), level);
		this.setPos(positionX, positionY, positionZ);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = positionX;
		this.yo = positionY;
		this.zo = positionZ;
	}

	public StemBoatEntity(SpawnEntity spawnEntity, Level level) {
		this(WonderEntities.STEM_BOAT.get(), level);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("WoodType", this.getWonderBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("WoodType", 8)) this.setWLBoatType(WonderBoatTypes.byName(compound.getString("WoodType")));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, WonderBoatTypes.STEM.ordinal());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setWLBoatType(WonderBoatTypes type) {
		this.entityData.set(BOAT_TYPE, type.ordinal());
	}

	public WonderBoatTypes getWonderBoatType() {
		return WonderBoatTypes.byId(this.entityData.get(BOAT_TYPE));
	}

	@Override
	public Boat.Type getVariant() {
		return Boat.Type.OAK;
	}

	public void setVariant(WonderBoatTypes type) {
	}

	@Override
	public Item getDropItem() {
		switch(this.getWonderBoatType()) {
		case STEM:
		default:
			return WonderItems.STEM_BOAT.get();
		}
	}
	
	public enum WonderBoatTypes {
		STEM(WonderBlocks.STEM_PLANKS.get(), "stem");
		
		private final String name;
		private final Block planks;
		
		WonderBoatTypes(Block planks, String name) {
			this.name = name;
			this.planks = planks;
		}
		
		public String getName() {
			return this.name;
		}
		
		public Block getPlanks() {
			return this.planks;
		}
		
		public String toString() {
			return this.name;
		}
		
		public static WonderBoatTypes byId(int id) {
			WonderBoatTypes[] boatEntityType = values();
			if (id < 0 || id >= boatEntityType.length) {
				id = 0;
			}
			return boatEntityType[id];
		}
		
		public static WonderBoatTypes byName(String name) {
			WonderBoatTypes[] boatEntityType = values();
			
			for (int i = 0; i < boatEntityType.length; ++i) {
				if (boatEntityType[i].getName().equals(name)) {
					return boatEntityType[i];
				}
			}
			return boatEntityType[0];
		}
		
	}
}
