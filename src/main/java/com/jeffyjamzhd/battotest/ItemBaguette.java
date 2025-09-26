package com.jeffyjamzhd.battotest;

import net.minecraft.src.*;

public class ItemBaguette extends ItemFood {
    public ItemBaguette(int id) {
        super(id, 0, 0, false, false);
        setTextureName("battotest:baguette");
        setUnlocalizedName("baguette");
        setCreativeTab(CreativeTabs.tabFood);
    }

    @Override
    public void updateUsingItem(ItemStack stack, World world, EntityPlayer player) {
        if (player.getItemInUseCount() < getMaxItemUseDuration(stack) - 11 && player.getItemInUseCount() % 4 == 0) {
            this.performUseEffects(player);
        }
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        if (!world.isRemote)
            player.getFoodStats().setFoodSaturationLevel(20);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 600;
    }

    @Override
    public int getItemUseWarmupDuration() {
        return 12;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.miscUse;
    }

    protected void performUseEffects(EntityPlayer player) {
        player.playSound("random.eat", 0.5F + 0.5F * (float)player.rand.nextInt(2), getPitchFromStack(player));
        if (player.worldObj.isRemote) {
            for(int var3 = 0; var3 < 5; ++var3) {
                Vec3 var4 = player.worldObj.getWorldVec3Pool().getVecFromPool(((double)player.rand.nextFloat() - (double)0.5F) * 0.1, Math.random() * 0.1 + 0.1, (double)0.0F);
                var4.rotateAroundX(-player.rotationPitch * (float)Math.PI / 180.0F);
                var4.rotateAroundY(-player.rotationYaw * (float)Math.PI / 180.0F);
                Vec3 var5 = player.worldObj.getWorldVec3Pool().getVecFromPool(((double)player.rand.nextFloat() - (double)0.5F) * 0.3, (double)(-player.rand.nextFloat()) * 0.6 - 0.3, 0.6);
                var5.rotateAroundX(-player.rotationPitch * (float)Math.PI / 180.0F);
                var5.rotateAroundY(-player.rotationYaw * (float)Math.PI / 180.0F);
                var5 = var5.addVector(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ);
                player.worldObj.spawnParticle("iconcrack_" + Item.bread.itemID, var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05, var4.zCoord);
            }
        }

    }

    protected float getPitchFromStack(EntityPlayer player) {
        return 0.5F + ((float) (getMaxItemUseDuration(null) - player.getItemInUseCount()) / getMaxItemUseDuration(null)) * 0.8F;
    }
}
