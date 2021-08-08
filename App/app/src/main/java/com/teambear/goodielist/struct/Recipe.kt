package com.teambear.goodielist.struct

enum class Category{
    BREAKFAST,
    LUNCH,
    SUPPER,
    DESSERT
}

data class Recipe(
        var id: Int,
        var name: String,
        var category: Category,
        var tags: List<String>,
        var ingredients: List<String>,
        var description: String,
        var steps: List<String>
)
