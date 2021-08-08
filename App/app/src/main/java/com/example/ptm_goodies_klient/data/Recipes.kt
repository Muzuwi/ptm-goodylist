package com.example.ptm_goodies_klient.data

import java.util.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object Recipes {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Recipe> = ArrayList()

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 0..COUNT) {
            addItem(createRecipe("rec" + i, "cat" + i, listOf("a", "b", "c"), "description"))
        }
    }

    private fun addItem(item: Recipe) {
        ITEMS.add(item)
    }

    private fun createRecipe(name: String, category: String, ingredients: List<String>, description: String): Recipe {
        return Recipe(ITEMS.count(), name, category, ingredients, description);
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun addRecipe(name: String, category: String, ingredients: String, descriprion: String) {
        addItem(createRecipe(name, category, listOf(ingredients), descriprion))
    }

    data class Recipe(
        var id: Int,
        var name: String,
        var category: String,
        var ingredients: List<String>,
        var description: String
    ) {

    }
}