package codyhuh.worldofwonder.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.core.other.WonderModelLayers;
import codyhuh.worldofwonder.client.renderer.entity.model.DandeLionModel;
import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DandeLionRenderer extends MobRenderer<DandeLionEntity, DandeLionModel<DandeLionEntity>> {
    private static final ResourceLocation FLUFF = new ResourceLocation(WorldOfWonder.MOD_ID, "textures/entity/dande_lion/fluff.png");
    private static final ResourceLocation NORMAL = new ResourceLocation(WorldOfWonder.MOD_ID, "textures/entity/dande_lion/normal.png");

	public DandeLionRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new DandeLionModel<>(renderManagerIn.bakeLayer(WonderModelLayers.DANDELION)), 0.25F);
    }

    @Override
    protected void scale(DandeLionEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        if (entitylivingbaseIn.isInSittingPose()) matrixStackIn.translate(0, entitylivingbaseIn.isBaby() ? 0.175 : 0.35, 0);
    }

    @Override
    public ResourceLocation getTextureLocation(DandeLionEntity entity) {
        return entity.isSheared() ? NORMAL : FLUFF;
    }
}
