package codyhuh.worldofwonder.client.renderer.entity;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.common.entity.StemBoatEntity.WonderBoatTypes;
import codyhuh.worldofwonder.common.entity.StemChestBoatEntity;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class StemChestBoatRenderer extends BoatRenderer {
	private final Map<WonderBoatTypes, Pair<ResourceLocation, ListModel<Boat>>> modChestBoatResources;

    public StemChestBoatRenderer(EntityRendererProvider.Context renderContext, boolean isChestBoot) {
        super(renderContext, isChestBoot);

                modChestBoatResources = Stream.of(WonderBoatTypes.values()).collect(ImmutableMap.toImmutableMap((boatType) -> {
            return boatType;
        }, (boatType) -> {
            return Pair.of(
                    new ResourceLocation(WorldOfWonder.MOD_ID, "textures/entity/chest_boat/" + boatType.getName() + ".png"),
                    new ChestBoatModel(renderContext.bakeLayer(
                            new ModelLayerLocation(
                                    new ResourceLocation("chest_boat/oak"),
                                    "main"
                            )
                    ))
            );
        }));
    }

    public StemChestBoatRenderer(EntityRendererProvider.Context renderContext) {
        this(renderContext, true);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
    	StemChestBoatEntity moddedBoat = (StemChestBoatEntity) boat;
        return modChestBoatResources.get(moddedBoat.getWonderChestBoatType());
    }
}
