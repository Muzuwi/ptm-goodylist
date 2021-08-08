package com.teambear.goodielist.struct

import java.util.*

enum class Category{
    BREAKFAST,
    LUNCH,
    SUPPER,
    DESSERT
}

data class Recipe(
        var username: String,
        var id: Int,
        var created: Date,
        var name: String,
        var category: Category,
        var tags: List<String>,
        var ingredients: List<String>,
        var description: String,
        var steps: List<String>
)
