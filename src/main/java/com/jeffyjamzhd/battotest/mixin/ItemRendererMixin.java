package com.jeffyjamzhd.battotest.mixin;

import com.jeffyjamzhd.battotest.ItemBaguette;
import com.jeffyjamzhd.battotest.render.BaguetteRenderer;
import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, remap = false)
public class ItemRendererMixin {
    @Shadow private ItemStack itemToRender;
    @Shadow private float prevEquippedProgress;
    @Shadow private float equippedProgress;
    @Shadow private Minecraft mc;

    @Inject(method = "renderItemInFirstPerson", at = @At(
            value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 3),
            cancellable = true)
    private void renderBaguetteInHandHehe(float tickDelta, CallbackInfo ci) {
        ItemStack stack = this.itemToRender;
        float equipTime = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * tickDelta;
        EntityPlayer player = this.mc.thePlayer;

        if (stack.getItem() instanceof ItemBaguette) {
            BaguetteRenderer.renderBaguetteFirstPerson((ItemRenderer) (Object) this,
                    tickDelta, equipTime, stack, player);
            GL11.glDisable(32826);
            RenderHelper.disableStandardItemLighting();
            ci.cancel();
        }
    }

    @Inject(method = "renderItem", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/src/TextureManager;bindTexture(Lnet/minecraft/src/ResourceLocation;)V", ordinal = 1),
            cancellable = true)
    private void renderBaguetteHehe(EntityLivingBase entity, ItemStack stack, int pass, CallbackInfo ci) {
        if (stack != null && stack.getItem() instanceof ItemBaguette) {
            Icon icon = CITUtils.getIcon(entity.getItemIcon(stack, pass), stack, pass);
            BaguetteRenderer.renderBaguette((ItemRenderer) (Object) this, stack, icon);
            ci.cancel();

            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }

    }
}
