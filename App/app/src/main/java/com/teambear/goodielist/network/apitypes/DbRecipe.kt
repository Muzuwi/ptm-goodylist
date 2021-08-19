package com.teambear.goodielist.network.apitypes

import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.models.RecipeContents
import com.teambear.goodielist.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

@Serializable
data class DbRecipe(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,

    val username: String,

    val created: Long,

    val json: String
) {
    companion object {
        fun toRecipe(dbRecipe: DbRecipe): Recipe {
            val contents: RecipeContents = Json.decodeFromString(dbRecipe.json)

            return Recipe(
                dbRecipe.id,
                dbRecipe.username,
                dbRecipe.created,
                contents.name,
                contents.tags,
                contents.ingredients,
                contents.description,
                contents.steps
            )
        }

        fun fromRecipe(recipe: Recipe): DbRecipe {
            val contents: RecipeContents = RecipeContents(
                recipe.name, recipe.tags, recipe.ingredients, recipe.description, recipe.steps
            )
            val jsonContents = Json.encodeToString(contents)

            return DbRecipe(
                recipe.id,
                recipe.username,
                recipe.created,
                jsonContents
            )
        }
    }
}
