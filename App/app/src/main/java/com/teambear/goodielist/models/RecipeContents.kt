package com.teambear.goodielist.models

import kotlinx.serialization.Serializable

@Serializable
data class RecipeContents(
    var name: String,

    var tags: List<String>,

    var ingredients: List<String>,

    var description: String,

    var steps: List<String>
)
