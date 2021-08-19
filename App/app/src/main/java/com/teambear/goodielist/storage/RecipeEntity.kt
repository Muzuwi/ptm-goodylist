package com.teambear.goodielist.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.models.RecipeContents
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

@Entity
internal data class RecipeEntity(
    @PrimaryKey
    val id: UUID,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "created")
    val created: Long,

    @ColumnInfo(name = "json")
    val json: String,
) {
    companion object {
        fun toRecipe(recipeEntity: RecipeEntity): Recipe {
            val contents: RecipeContents = Json.decodeFromString(recipeEntity.json)

            return Recipe(
                recipeEntity.id,
                recipeEntity.username,
                recipeEntity.created,
                contents.name,
                contents.tags,
                contents.ingredients,
                contents.description,
                contents.steps
            )
        }

        fun fromRecipe(recipe: Recipe): RecipeEntity {
            val contents = RecipeContents(
                recipe.name, recipe.tags, recipe.ingredients, recipe.description, recipe.steps
            )
            val jsonContents = Json.encodeToString(contents)

            return RecipeEntity(
                recipe.id,
                recipe.username,
                recipe.created,
                jsonContents
            )
        }
    }

}
