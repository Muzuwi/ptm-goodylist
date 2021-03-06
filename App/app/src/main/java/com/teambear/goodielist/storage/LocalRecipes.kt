package com.teambear.goodielist.storage
import android.content.Context
import androidx.room.Room
import com.teambear.goodielist.interfaces.IUserRecipeWorker
import com.teambear.goodielist.models.Recipe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

object LocalRecipes : IUserRecipeWorker {
    private lateinit var database: RecipeDatabase

    fun Init(context: Context) {
        database = Room.databaseBuilder(
            context,
            RecipeDatabase::class.java, "goodielist")
            .addTypeConverter(UuidDbConverter())
            .allowMainThreadQueries()
            .build()
        if(database.recipeDao().GetAllEntities().isEmpty()) {
            CreateDummyEntries()
        }
    }

    private fun CreateDummyEntries() {
        return
//        var recipes = listOf(
//            Recipe(UUID.randomUUID(), "test", System.currentTimeMillis() / 1000L,"Costam", RecipeCategory.BREAKFAST, listOf(), listOf(), "Pyszne śniadanie", listOf()),
//            Recipe(UUID.randomUUID(), "test", System.currentTimeMillis() / 1000L,"Obiadek", RecipeCategory.LUNCH, listOf(), listOf(), "Pyszny obiadek", listOf()),
//            Recipe(UUID.randomUUID(), "test", System.currentTimeMillis() / 1000L,"Wieczorne klimaty", RecipeCategory.SUPPER, listOf(), listOf(), "Cos na zapchanie", listOf()),
//            Recipe(UUID.randomUUID(), "test", System.currentTimeMillis() / 1000L,"Deserowy czerwiec", RecipeCategory.DESSERT, listOf(), listOf(), "woooo! yeah!", listOf()),
//            Recipe(UUID.randomUUID(), "test", System.currentTimeMillis() / 1000L,"Shokugeki no Soma", RecipeCategory.LUNCH, listOf(), listOf(), "oppai", listOf()),
//        )
//        for (recipe in recipes) {
//            System.out.println("Dodawanie przepisu " + recipe.id.toString())
//            val json = Json.encodeToString(recipe)
//            val newEntity = RecipeEntity(recipe.id, recipe.username, json)
//
//            database.recipeDao().InsertEntity(newEntity)
//        }
    }

    private fun GetLocalRecipes(): List<Recipe>? {
        var entities = database.recipeDao().GetAllEntities()

        var result = mutableListOf<Recipe>()
        for (entity in entities) {
            result.add(RecipeEntity.toRecipe(entity))
            println(entity.json)
        }

        return result
    }

    private fun GetLocalTagList(tagList: List<Recipe>): HashMap<String, Int>{
        var result: MutableMap<String, Int> = mutableMapOf<String, Int>()
        tagList.forEach {
            it.tags.forEach {
                if(!result.contains(it)) result[it] = 1
                else result[it] = (result[it]!! + 1)
            }
        }
        return result as HashMap<String, Int>
    }

    private fun GetLocalRecipeByID(id: UUID): Recipe? {
        val entity = database.recipeDao().GetEntityByID(id) ?: return null

        return RecipeEntity.toRecipe(entity)
    }

    private fun GetLocalRecipesByName(name: String): List<Recipe>? {
        val entities = database.recipeDao().GetEntitiesByJson("%\"name\":\"${name}%") ?: return null

        var result = mutableListOf<Recipe>()
        for (entity in entities) {
            result.add(RecipeEntity.toRecipe(entity))
            println(entity.json)
        }

        return result
    }

    private fun UpdateLocalRecipe(id: UUID, recipe: Recipe): Boolean {
        val entity = RecipeEntity.fromRecipe(recipe)

        database.recipeDao().UpdateEntity(entity)
        return true
    }

    private fun InsertLocalRecipe(newRecipe: Recipe): Boolean {
        val entity = RecipeEntity.fromRecipe(newRecipe)

        try {
            database.recipeDao().InsertEntity(entity)
        } catch (ex: Exception) {
            return false
        }

        return true
    }

    private fun DeleteLocalRecipe(id: UUID): Boolean {
        val entity = database.recipeDao().GetEntityByID(id) ?: return false
        database.recipeDao().DeleteEntity(entity)
        return true
    }

    /*
        Implementacje interfejsu IUserRecipeWorker
     */

    override fun GetRecipeByUUID(id: UUID): Recipe? {
        return GetLocalRecipeByID(id)
    }

    override fun GetAllRecipes(): List<Recipe>? {
        return GetLocalRecipes()
    }

    override fun GetRecipeCount(): Int {
        //  FIXME: Podmienić na coś innego zamiast ładować całą bazę znowu
        return GetLocalRecipes()?.size ?: 0
    }

    override fun GetRecipesByName(name: String): List<Recipe>? {
        return GetLocalRecipesByName(name)
    }

    override fun UpdateRecipe(id: UUID, newRecipe: Recipe): Boolean {
        return UpdateLocalRecipe(id, newRecipe)
    }

    override fun InsertRecipe(newRecipe: Recipe): Boolean {
        return InsertLocalRecipe(newRecipe)
    }

    override fun DeleteRecipe(id: UUID): Boolean {
        return DeleteLocalRecipe(id)
    }

    public fun GetTagList(): HashMap<String, Int>? {
        return GetAllRecipes()?.let { GetLocalTagList(it) }
    }
}