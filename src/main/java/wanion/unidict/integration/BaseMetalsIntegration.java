package wanion.unidict.integration;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 1.1. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/1.1/.
 */

import cyano.basemetals.registry.CrusherRecipeRegistry;
import cyano.basemetals.registry.recipe.ICrusherRecipe;
import cyano.basemetals.registry.recipe.OreDictionaryCrusherRecipe;
import net.minecraft.item.ItemStack;
import wanion.unidict.UniDict;
import wanion.unidict.UniOreDictionary;
import wanion.unidict.common.Util;
import wanion.unidict.helper.LogHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class BaseMetalsIntegration extends AbstractIntegrationThread
{
    private final UniOreDictionary uniOreDictionary = UniDict.getDependencies().get(UniOreDictionary.class);

    BaseMetalsIntegration()
    {
        super("Base Metals");
        uniOreDictionary.prepare();
    }

    @Override
    public String call()
    {
        try {
            fixCrackHammerDrops();
        } catch (Exception e) { LogHelper.error(e); }
        return threadName + "When smashing things with Crack Hammer, you will get the right things now.";
    }

    private void fixCrackHammerDrops()
    {
        List<ICrusherRecipe> crusherRecipes = Util.getField(CrusherRecipeRegistry.class, "recipes", CrusherRecipeRegistry.getInstance(), List.class);
        if (crusherRecipes == null)
            return;
        List<ICrusherRecipe> newRecipes = new ArrayList<>();
        for (Iterator<ICrusherRecipe> crusherRecipeIterator = crusherRecipes.iterator(); crusherRecipeIterator.hasNext();)
        {
            ICrusherRecipe crusherRecipe = crusherRecipeIterator.next();
            if (crusherRecipe instanceof OreDictionaryCrusherRecipe) {
                ItemStack output = crusherRecipe.getOutput();
                ItemStack correctOutput = resourceHandler.getMainItemStack(output);
                if (correctOutput == output)
                    continue;
                newRecipes.add(new OreDictionaryCrusherRecipe(uniOreDictionary.getName(crusherRecipe.getValidInputs()), correctOutput));
                crusherRecipeIterator.remove();
            }
        }
        crusherRecipes.addAll(newRecipes);
    }
}
