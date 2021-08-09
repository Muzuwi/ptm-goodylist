package com.teambear.goodielist.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(RecipeEntity::class), version = 1)
@TypeConverters(UuidDbConverter::class)
internal abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
