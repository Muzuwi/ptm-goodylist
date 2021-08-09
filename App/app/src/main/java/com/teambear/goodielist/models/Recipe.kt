package com.teambear.goodielist.models

import com.teambear.goodielist.serializers.DateSerializer
import com.teambear.goodielist.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Recipe (
    @Serializable(with = UUIDSerializer::class)
    var id: UUID,

    var username: String,

    @Serializable(with = DateSerializer::class)
    var created: Date,

    var name: String,

    var category: RecipeCategory,

    var tags: List<String>,

    var ingredients: List<String>,

    var description: String,

    var steps: List<String>
)
