package codyhuh.worldofwonder.client.renderer.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import codyhuh.worldofwonder.WorldOfWonder;
import codyhuh.worldofwonder.client.ClientEvents;
import codyhuh.worldofwonder.common.entity.DandeLionSeedEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class DandeLionSeedRenderer extends EntityRenderer<DandeLionSeedEntity> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(WorldOfWonder.MOD_ID, "textures/entity/dande_lion/seed.png");
	private final ModelPart part;

	public DandeLionSeedRenderer(EntityRendererProvider.Context context) {
		super(context);
		ModelPart modelpart = context.bakeLayer(ClientEvents.DANDELION_SEED);
		this.part = modelpart.getChild("part");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createSeed() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition part = partdefinition.addOrReplaceChild("part", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 6, 6);
	}

	@Override
	public void render(DandeLionSeedEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		RenderSystem.enableDepthTest();
		RenderSystem.depthMask(true);
		float scale = 1.0F;
		matrixStackIn.scale(-scale, -scale, scale);
		matrixStackIn.translate(0.0F, -1.5F, 0.0F);
		part.render(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entityIn))), packedLightIn, OverlayTexture.NO_OVERLAY);
		RenderSystem.depthMask(true);
		matrixStackIn.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(DandeLionSeedEntity entity) {
		return TEXTURE;
	}
}
