package com.jeffyjamzhd.battotest;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.crafting.recipe.RecipeManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Battotest extends BTWAddon {
    private static ItemBaguette baguette = new ItemBaguette(288);
    private static Battotest instance;

    public Battotest() {
        super();
        instance = this;
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        RecipeManager.addSoulforgeRecipe(new ItemStack(baguette), new Object[]{
                "   B",
                "  B ",
                " B  ",
                "B   ",
                'B', Item.bread
        });
    }
}