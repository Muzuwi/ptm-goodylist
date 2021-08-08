package com.example.ptm_goodies_klient.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.ptm_goodies_klient.R
import com.example.ptm_goodies_klient.data.Recipes

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeFragment : Fragment() {
    lateinit var nameInput: EditText
    lateinit var categoryInput: EditText
    lateinit var ingredientsInput: EditText
    lateinit var descriprionInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameInput = view.findViewById(R.id.newRecipeName)
        categoryInput = view.findViewById(R.id.newRecipeCategory)
        ingredientsInput = view.findViewById(R.id.newRecipeIngredients)
        descriprionInput = view.findViewById(R.id.newRecipeDescription)

        val saveButton = view.findViewById<Button>(R.id.addButton)
        saveButton.setOnClickListener {
            addNewRecipe()
        }
    }

    private fun addNewRecipe() {
        var name: String = nameInput.text.toString()
        var category: String = categoryInput.text.toString()
        var ingredients: String = ingredientsInput.text.toString()
        var descriprion: String = descriprionInput.text.toString()

        if(name.isEmpty()) name = "Unnamed Recipe";
        if(category.isEmpty()) category = "Not Specified";
        if(ingredients.isEmpty()) ingredients = "No ingredients";
        if(descriprion.isEmpty()) descriprion = "No description";

        Recipes.addRecipe(name, category, ingredients, descriprion);
    }
}