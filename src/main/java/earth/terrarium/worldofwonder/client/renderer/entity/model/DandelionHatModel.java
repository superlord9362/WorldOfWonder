package earth.terrarium.worldofwonder.client.renderer.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DandelionHatModel extends HumanoidModel<LivingEntity> {

    public DandelionHatModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.getChild("head");

        PartDefinition bb_main = head.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 36).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
        		.texOffs(0, 29).addBox(-4.5F, -0.5F, -3.0F, 9.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-12, 52).addBox(-13.5F, 1.9F, -16.6F, 17.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -0.3927F, -1.5708F, 0.0F));

        bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-12, 52).addBox(-3.5F, 1.9F, -16.6F, 17.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -0.3927F, 1.5708F, 0.0F));

        bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-12, 52).mirror().addBox(-8.5F, 0.0F, -12.0F, 17.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -5.0F, -0.3927F, 0.0F, 0.0F));

        bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-12, 52).addBox(-8.5F, 0.0F, -12.0F, 17.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -0.3927F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

}
	