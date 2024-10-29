package codyhuh.worldofwonder.client.renderer.entity.model;

import codyhuh.worldofwonder.common.entity.DandeLionEntity;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class DandeLionModel<T extends DandeLionEntity> extends AgeableHierarchicalModel<DandeLionEntity> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart armLeft;
	private final ModelPart armRight;
	private final ModelPart legLeft;
	private final ModelPart legRight;
	private final ModelPart earLeft;
	private final ModelPart earRight;

	public DandeLionModel(ModelPart root) {
        super(0.5F, 24.0F);
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.earLeft = head.getChild("earLeft");
		this.earRight = head.getChild("earRight");
		this.tail = body.getChild("tail");
		this.armLeft = body.getChild("armLeft");
		this.armRight = body.getChild("armRight");
		this.legLeft = body.getChild("legLeft");
		this.legRight = body.getChild("legRight");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition legRight = body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 4.0F, 5.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 49).addBox(-3.5F, -3.0F, -5.0F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.0F));

		PartDefinition earRight = head.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(25, 22).addBox(-2.0F, -2.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, -2.5F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(24, 49).addBox(-2.5F, -1.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -5.0F));

		PartDefinition mane = head.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(0, 22).addBox(-4.5F, -2.0F, 0.0F, 9.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.0F));

		PartDefinition maneExtra = mane.addOrReplaceChild("maneExtra", CubeListBuilder.create().texOffs(0, 40).addBox(-4.5F, 9.0F, -10.5F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.5F));

		PartDefinition earLeft = head.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(25, 22).addBox(-1.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -3.0F, -2.5F));

		PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 5.0F, 5.0F));

		PartDefinition armLeft = body.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 4.0F, -5.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(30, 0).addBox(-1.0F, 0.1302F, -1.2614F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.25F, 6.75F, 0.1745F, 0.0F, 0.0F));

		PartDefinition armRight = body.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 4.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void setupAnim(DandeLionEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        body.getAllParts().forEach(ModelPart::resetPose);

        float speed = 1.0f;
        float degree = 1.0f;

        if (entityIn.isInSittingPose()) {
            this.legRight.y = 2.0F;
            this.legRight.xRot = 1.5708F;
            this.legRight.yRot = -0.4363F;

            this.legLeft.y = 2.0F;
            this.legLeft.xRot = 1.5708F;
            this.legLeft.yRot = 0.4363F;

            this.armRight.y = 2.0F;
            this.armRight.xRot = -1.5708F;
            this.armRight.yRot = 0.4363F;

            this.armLeft.y = 2.0F;
            this.armLeft.xRot = -1.5708F;
            this.armLeft.yRot = -0.4363F;

            this.body.y = 14.5F;
            this.body.xRot = 0.0F;

            this.head.y = -5.0F;

            this.tail.xRot = 1.0471975511965976F;
        } else {
            this.body.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
            this.head.y = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.05F * limbSwingAmount - 3.05F;
            this.legRight.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legLeft.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.armRight.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.armLeft.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.tail.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.4F;

            this.armLeft.yRot = 0.0F;
            this.armRight.yRot = 0.0F;
            this.legLeft.yRot = 0.0F;
            this.legRight.yRot = 0.0F;

            this.armLeft.y = 4.0F;
            this.armRight.y = 4.0F;
            this.legLeft.y = 4.0F;
            this.legRight.y = 4.0F;

        }

        this.earRight.zRot = Mth.cos(-1.0F + ageInTicks * speed * 0.15F) * degree * 0.2F - 0.1F;
        this.earLeft.zRot = Mth.cos(-1.0F + ageInTicks * speed * 0.15F) * degree * -0.2F + 0.1F;
        this.tail.zRot = Mth.cos(ageInTicks * 0.25F) * 0.25F;
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
