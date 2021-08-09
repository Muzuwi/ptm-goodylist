package com.teambear.goodielist.storage

import androidx.room.*
import java.util.*

@Dao
internal interface RecipeDao {
    @Query("SELECT * FROM RecipeEntity")
    fun GetAllEntities(): List<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity WHERE id = :targetID")
    fun GetEntityByID(targetID: UUID): RecipeEntity?

    @Insert
    fun InsertEntity(recipeEntity: RecipeEntity)

    @Delete
    fun DeleteEntity(recipeEntity: RecipeEntity)

    @Update
    fun UpdateEntity(recipeEntity: RecipeEntity)

}