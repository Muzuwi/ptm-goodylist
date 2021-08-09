package com.teambear.goodielist.interfaces

import com.teambear.goodielist.models.Recipe

interface IRecipeListViewer {
    fun GetItemCount(): Int

    fun GetItemByPostion(position: Int): Recipe?
}