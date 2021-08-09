package com.teambear.goodielist.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
internal data class RecipeEntity(
    @PrimaryKey
    val id: UUID,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "json")
    val json: String,
)
