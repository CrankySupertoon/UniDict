package wanion.unidict.recipe;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import wanion.unidict.UniOreDictionary;
import wanion.unidict.resource.ResourceHandler;

import javax.annotation.Nonnull;
import java.util.List;

public interface IRecipeResearcher<S extends IRecipe, L extends IRecipe>
{
	int getShapedRecipeKey(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler);

	int getShapelessRecipeKey(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler);

	@Nonnull
	List<Class<? extends S>> getShapedRecipeClasses();

	@Nonnull
	List<Class<? extends L>> getShapelessRecipeClasses();

	@Nonnull
	ShapedOreRecipe getNewShapedRecipe(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler, @Nonnull final UniOreDictionary uniOreDictionary);

	@Nonnull
	ShapedOreRecipe getNewShapedFromShapelessRecipe(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler, @Nonnull final UniOreDictionary uniOreDictionary);

	@Nonnull
	ShapelessOreRecipe getNewShapelessRecipe(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler, @Nonnull final UniOreDictionary uniOreDictionary);

	@Nonnull
	ShapelessOreRecipe getNewShapelessFromShapedRecipe(@Nonnull final IRecipe recipe, @Nonnull final ResourceHandler resourceHandler, @Nonnull final UniOreDictionary uniOreDictionary);
}