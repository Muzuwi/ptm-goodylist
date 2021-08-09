package com.teambear.goodielist.workers

import com.teambear.goodielist.interfaces.IRecipeListViewer
import com.teambear.goodielist.interfaces.IUserRecipeWorker
import com.teambear.goodielist.models.Recipe

class DummyRecipeListViewer (
    val recipeWorker: IUserRecipeWorker
    ) : IRecipeListViewer {

    val allRecipes = recipeWorker.GetAllRecipes()

    override fun GetItemCount(): Int {
        allRecipes ?: return 0
        return allRecipes.size
    }

    override fun GetItemByPostion(position: Int): Recipe? {
        if(allRecipes == null) {
            return null
        }
        if (allRecipes.size < position){
            return null
        }
        return allRecipes[position]
    }
}