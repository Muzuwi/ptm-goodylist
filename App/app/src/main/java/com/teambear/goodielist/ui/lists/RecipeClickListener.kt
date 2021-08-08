package com.teambear.goodielist.ui.lists

interface RecipeClickListener {
    fun OnRecipeClick(position: Int)

    fun OnRecipeLongClick(position: Int)
}