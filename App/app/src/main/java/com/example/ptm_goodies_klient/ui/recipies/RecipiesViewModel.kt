package com.example.ptm_goodies_klient.ui.recipies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ptm_goodies_klient.res.Recipe

class RecipiesViewModel : ViewModel() {

    private var recipes = listOf<Recipe>(Recipe(1, "name1", "cat1", listOf("in1","in2","in3"),"desc1"));

    private val _text = MutableLiveData<String>().apply {
        value = "Recipe " + recipes[0].id + ": " + recipes[0].name + "\n" + recipes[0].category + "\n" + recipes[0].description }

    val text: LiveData<String> = _text
}