package com.teambear.goodielist.models

import com.teambear.goodielist.models.RecipeCategory
import java.util.*

data class Recipe(
    var username: String,
    var id: UUID,
    var created: Date,
    var name: String,
    var category: RecipeCategory,
    var tags: List<String>,
    var ingredients: List<String>,
    var description: String,
    var steps: List<String>
)
