package com.teambear.goodielist.interfaces

import com.teambear.goodielist.models.Recipe
import java.util.*

interface IUserRecipeWorker {
    fun GetRecipeByUUID(id: UUID): Recipe?

    fun GetAllRecipes(): List<Recipe>?

    fun GetRecipeCount(): Int

    fun UpdateRecipe(id: UUID, newRecipe: Recipe): Boolean

    fun InsertRecipe(newRecipe: Recipe): Boolean

    fun DeleteRecipe(id: UUID): Boolean

}