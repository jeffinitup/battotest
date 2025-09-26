package com.jeffyjamzhd.battotest.render;

import com.prupe.mcpatcher.cc.ColorizeBlock;
import com.prupe.mcpatcher.mal.block.BlockAPI;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class BaguetteRenderer {
    public static void renderBaguetteFirstPerson(ItemRenderer renderer,
            float tickDelta, float equipTime, ItemStack stack, EntityPlayer player) {
        GL11.glPushMatrix();

        float var20 = 0.8F;
        if (player.getItemInUseCount() > 0) {
            EnumAction var22 = stack.getItemUseAction();
            if (var22 != EnumAction.eat && var22 != EnumAction.drink) {
                if (var22 == EnumAction.miscUse) {
                    float var23 = (float)player.getItemInUseCount() - tickDelta + 1.0F;
                    float var14 = var23 / (float)stack.getMaxItemUseDuration();
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    float var15 = 1.0F - var14;
                    GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var23 / 8.0F * (float)Math.PI) * 0.02F) * (stack.getMaxItemUseDuration() - player.getItemInUseCount() >= stack.getItem().getItemUseWarmupDuration() ? 1.0F : 0.0F), 0.0F);
                    int iItemInUseCount = MathHelper.clamp_int(32 - (stack.getMaxItemUseDuration() - player.getItemInUseCount()), 0, 32);
                    float itemUseInterpolation =
                            ((float) Math.max(0, stack.getMaxItemUseDuration() - var23 - 12) / stack.getMaxItemUseDuration());
                    var23 = (float)iItemInUseCount - tickDelta + 1.0F;
                    var14 = var23 / 32.0F;
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    var15 = 1.0F - var14;
                    GL11.glTranslatef(var15 * .45F, .2F, 12.5F * itemUseInterpolation);
                    GL11.glRotatef(var15 * 111.75F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-var15 * 90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(var15 * 2F, 1.0F, 0.0F, 0.0F);

                }
            } else {
                float var23 = (float)player.getItemInUseCount() - tickDelta + 1.0F;
                float var13 = 1.0F - var23 / (float)stack.getMaxItemUseDuration();
                float var14 = 1.0F - var13;
                var14 = var14 * var14 * var14;
                var14 = var14 * var14 * var14;
                var14 = var14 * var14 * var14;
                float var15 = 1.0F - var14;
                GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var23 / 4.0F * (float)Math.PI) * 0.1F) * (float)((double)var13 > 0.2 ? 1 : 0), 0.0F);
                GL11.glTranslatef(var15 * 0.8F, -var15 * 0.8F, 0.0F);
                GL11.glRotatef(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
            }
        } else {
            float var21 = player.getSwingProgress(tickDelta);
            float var23 = MathHelper.sin(var21 * (float)Math.PI);
            float var13 = MathHelper.sin(MathHelper.sqrt_float(var21) * (float)Math.PI);
            GL11.glTranslatef(-var13 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var21) * (float)Math.PI * 2.0F) * 0.2F, -var23 * 0.2F);
        }

        GL11.glTranslatef(0.7F * var20, -0.65F * var20 - (1.0F - equipTime) * 0.6F, -0.9F * var20);
        GL11.glScalef(.5F, 1.5F, 1.5F);
        GL11.glTranslatef(-0.2F, 0.015F, 0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(32826);
        float var21 = player.getSwingProgress(tickDelta);
        float var23 = MathHelper.sin(var21 * var21 * (float)Math.PI);
        float var13 = MathHelper.sin(MathHelper.sqrt_float(var21) * (float)Math.PI);
        GL11.glRotatef(-var23 * 20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-var13 * 20.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-var13 * 80.0F, 1.0F, 0.0F, 0.0F);
        float var14 = 0.4F;
        GL11.glScalef(var14, var14, var14);
        if (player.getItemInUseCount() > 0) {
            EnumAction var25 = stack.getItemUseAction();
            if (var25 == EnumAction.block) {
                GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            } else if (var25 == EnumAction.bow) {
                GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
                float var16 = (float)stack.getMaxItemUseDuration() - ((float)player.getItemInUseCount() - tickDelta + 1.0F);
                float var17 = var16 / 20.0F;
                var17 = (var17 * var17 + var17 * 2.0F) / 3.0F;
                if (var17 > 1.0F) {
                    var17 = 1.0F;
                }

                if (var17 > 0.1F) {
                    GL11.glTranslatef(0.0F, MathHelper.sin((var16 - 0.1F) * 1.3F) * 0.01F * (var17 - 0.1F), 0.0F);
                }

                GL11.glTranslatef(0.0F, 0.0F, var17 * 0.1F);
                GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                float var18 = 1.0F + var17 * 0.2F;
                GL11.glScalef(1.0F, 1.0F, var18);
                GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            }
        }

        if (stack.getItem().shouldRotateAroundWhenRendering()) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }

        renderer.renderItem(player, stack, 0);
        GL11.glPopMatrix();
    }

    public static void renderBaguette(ItemRenderer renderer, ItemStack stack, Icon icon) {
        TextureManager manager = Minecraft.getMinecraft().getTextureManager();

        manager.bindTexture(manager.getResourceLocation(stack.getItemSpriteNumber()));
        Tessellator var6 = Tessellator.instance;
        float var7 = icon.getMinU();
        float var8 = icon.getMaxU();
        float var9 = icon.getMinV();
        float var10 = icon.getMaxV();
        float var11 = 0.0F;
        float var12 = 0.3F;
        GL11.glEnable(32826);
        GL11.glTranslatef(-var11, -var12, 0.0F);
        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(16F, 16F, 1F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        ColorizeBlock.colorizeWaterBlockGL(BlockAPI.getBlockById(stack.itemID));
        ItemRenderer.renderItemIn2D(var6, var8, var9, var7, var10, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
    }
}
