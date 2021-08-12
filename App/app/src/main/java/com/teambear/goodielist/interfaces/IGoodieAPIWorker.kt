package com.teambear.goodielist.interfaces

import com.teambear.goodielist.models.Recipe
import java.util.*

interface IGoodieAPIWorker {
    suspend fun RegisterUser(username: String, password: String): Boolean

    suspend fun LoginUser(username: String, password: String): UUID?

    suspend fun ValidateToken(token: UUID): Boolean

    suspend fun Logout(token: UUID): Boolean

    suspend fun FetchRecipe(token: UUID, recipe: UUID): Recipe?

    suspend fun FetchRecentRecipes(token: UUID): List<Recipe>?

    suspend fun CreateRecipe(token: UUID, recipe: Recipe): Boolean

    suspend fun UpdateRecipe(token: UUID, recipe: Recipe): Boolean

    suspend fun FetchUserRecipes(token: UUID): List<Recipe>?

}