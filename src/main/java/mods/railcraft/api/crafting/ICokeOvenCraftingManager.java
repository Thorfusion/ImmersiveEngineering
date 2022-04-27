/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface ICokeOvenCraftingManager {

    void addRecipe(ItemStack input, boolean matchDamage, boolean matchNBT, ItemStack output, FluidStack liquidOutput, int cookTime);

    ICokeOvenRecipe getRecipe(ItemStack stack);

    List<? extends ICokeOvenRecipe> getRecipes();
}
