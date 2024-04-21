package codyhuh.worldofwonder.client.renderer.tileentity;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WonderItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final BlockEntityRenderDispatcher dispatcher;
    private final Supplier<? extends BlockEntityType<?>> entitySupplier;
    private final Supplier<BlockEntity> entity;

    public WonderItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet, Supplier<? extends BlockEntityType<?>> type, Supplier<BlockState> blockStateSupplier) {
        super(dispatcher, modelSet);
        this.dispatcher = dispatcher;
        entitySupplier = type;
        entity = Suppliers.memoize(() -> entitySupplier.get().create(BlockPos.ZERO, blockStateSupplier.get()));
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        dispatcher.renderItem(entity.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}
