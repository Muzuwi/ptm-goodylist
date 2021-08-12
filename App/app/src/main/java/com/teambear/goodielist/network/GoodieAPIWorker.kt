package com.teambear.goodielist.network

import com.teambear.goodielist.interfaces.IGoodieAPIWorker
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.network.apitypes.DbRecipe
import com.teambear.goodielist.network.apitypes.RegisterInfo
import com.teambear.goodielist.network.apitypes.UserLogin
import com.teambear.goodielist.network.apitypes.UserToken
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*

object GoodieAPIWorker : IGoodieAPIWorker {
    override suspend fun RegisterUser(username: String, password: String): Boolean {
        var ret = false
        try {
            GoodieREST.service.Register(RegisterInfo(username, password))
            ret = true
        } catch(ex: Exception) {
            println("Failed registering user: ${ex.message}")
        }

        return ret
    }

    override suspend fun LoginUser(username: String, password: String): UUID? {
        var token: UserToken? = null
        try {
            token = GoodieREST.service.Login(UserLogin(username, password))
        } catch(ex: Exception) {
            println("Login for user failed: " + ex.message)
        }

        return token?.token
    }

    override suspend fun Logout(token: UUID): Boolean {
        var ret = false
        try {
            GoodieREST.service.Logout(token)
            ret = true
        } catch(ex: Exception) {
            println("Failed logging out: ${ex.message}")
        }

        return ret
    }

    override suspend fun ValidateToken(token: UUID): Boolean {
        var ret = false
        try {
            GoodieREST.service.Validate(token)
            ret = true
        } catch(ex: Exception) {
            println("Token validation failed: ${ex.message}")
        }

        return ret
    }

    override suspend fun FetchUserRecipes(token: UUID): List<Recipe>? {
        var recipes: MutableList<Recipe>? = null
        try {
            var entities = GoodieREST.service.FetchUserRecipes(token)
            recipes = mutableListOf()

            for (entity in entities) {
                recipes.add(Json.decodeFromString(entity.json))
            }
        } catch(ex: Exception) {
            println("User recipe fetch failed: ${ex.message}")
        }

        return recipes
    }

    override suspend fun FetchRecipe(token: UUID, id: UUID): Recipe? {
        var recipe: Recipe? = null
        try {
            var entity = GoodieREST.service.FetchRecipe(token, id)
            recipe = Json.decodeFromString(entity.json)
        } catch(ex: Exception) {
            println("User recipe id=${id} fetch failed: ${ex.message}")
        }

        return recipe
    }

    override suspend fun FetchRecentRecipes(token: UUID): List<Recipe>? {
        var recipes: MutableList<Recipe>? = null
        try {
            val entities = GoodieREST.service.FetchRecentRecipes(token)

            recipes = mutableListOf()
            for (entity in entities) {
                recipes.add(Json.decodeFromString(entity.json))
            }
        } catch(ex: Exception) {
            println("User recent recipe fetch failed: ${ex.message}")
        }

        return recipes
    }

    override suspend fun CreateRecipe(token: UUID, recipe: Recipe): Boolean {
        var ret = false
        try {
            val entity = DbRecipe(
                recipe.id,
                recipe.username,
                Json.encodeToString(recipe)
            )

            GoodieREST.service.CreateRecipe(token, recipe.id, entity)
            ret = true
        } catch(ex: Exception) {
            println("Creating recipe id=${recipe.id} failed: ${ex.message}")
        }

        return ret
    }

    override suspend fun UpdateRecipe(token: UUID, recipe: Recipe): Boolean {
        var ret = false
        try {
            val entity = DbRecipe(
                recipe.id,
                recipe.username,
                Json.encodeToString(recipe)
            )

            GoodieREST.service.UpdateRecipe(token, recipe.id, entity)
            ret = true
        } catch(ex: Exception) {
            println("Updating recipe id=${recipe.id} failed: ${ex.message}")
        }

        return ret
    }
}