package com.teambear.goodielist.workers

import com.teambear.goodielist.interfaces.IUserRecipeWorker
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.models.Recipe
import java.util.*

class DummyUserRecipeWorker : IUserRecipeWorker {
    private var dummyRecipes: MutableList<Recipe> = mutableListOf<Recipe>(
        Recipe("test", UUID.randomUUID(), Calendar.getInstance().getTime(),"Costam", RecipeCategory.BREAKFAST, listOf(), listOf(), "Pyszne śniadanie", listOf()),
        Recipe("test", UUID.randomUUID(), Calendar.getInstance().getTime(),"Obiadek", RecipeCategory.LUNCH, listOf(), listOf(), "Pyszny obiadek", listOf()),
    );

    override fun GetRecipeByUUID(id: UUID): Recipe? {
        return dummyRecipes.find { it.id == id }
    }

    override fun GetAllRecipes(): List<Recipe>? {
        return dummyRecipes
    }

    override fun GetRecipeCount(): Int {
        return dummyRecipes.size
    }

    override fun UpdateRecipe(id: UUID, newRecipe: Recipe): Boolean {
        dummyRecipes.find { it.id == id } ?: return false

        val index = dummyRecipes.indexOfFirst { it.id == id }
        dummyRecipes[index] = newRecipe

        return true
    }

}