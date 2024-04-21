package earth.terrarium.worldofwonder.client.renderer.entity;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import earth.terrarium.worldofwonder.WorldOfWonder;
import earth.terrarium.worldofwonder.common.entity.StemBoatEntity;
import earth.terrarium.worldofwonder.common.entity.StemBoatEntity.WonderBoatTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class StemBoatRenderer extends BoatRenderer {
	private final Map<WonderBoatTypes, Pair<ResourceLocation, ListModel<Boat>>> modBoatResources;

    public StemBoatRenderer(EntityRendererProvider.Context renderContext, boolean isChestBoot) {
        super(renderContext, isChestBoot);
        modBoatResources = Stream.of(WonderBoatTypes.values()).collect(ImmutableMap.toImmutableMap((boatType) -> {
            return boatType;
        }, (boatType) -> {
            return Pair.of(
                new ResourceLocation(WorldOfWonder.MOD_ID, "textures/entity/boat/" + boatType.getName() + ".png"),
                new BoatModel(renderContext.bakeLayer(
                    new ModelLayerLocation(
                        new ResourceLocation("boat/oak"),
                        "main"
                    )
                ))
            );
        }));
    }

    public StemBoatRenderer(EntityRendererProvider.Context renderContext) {
        this(renderContext, false);
    }
    
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
    	StemBoatEntity moddedBoat = (StemBoatEntity) boat;
        return modBoatResources.get(moddedBoat.getWonderBoatType());
    }

	
}