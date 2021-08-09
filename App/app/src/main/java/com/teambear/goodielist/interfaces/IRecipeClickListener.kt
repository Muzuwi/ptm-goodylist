package com.teambear.goodielist.interfaces

import com.teambear.goodielist.models.Recipe

interface IRecipeClickListener {
    fun OnRecipeClick(recipe: Recipe)

    fun OnRecipeLongClick(recipe: Recipe)
}