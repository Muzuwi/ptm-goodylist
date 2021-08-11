package com.teambear.goodielist.interfaces

import android.view.View
import com.teambear.goodielist.models.Recipe

interface IRecipeClickListener {
    fun OnRecipeClick(recipe: Recipe)

    fun OnRecipeLongClick(view: View, recipe: Recipe)
}