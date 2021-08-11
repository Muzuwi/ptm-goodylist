package com.teambear.goodielist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.storage.LocalRecipes
import java.util.*
import android.widget.AdapterView

import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.adapters.EditTextBoxListAdapter


class FragmentRecipeEdit : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_recipe_edit, container, false)

        //SAVE BUTTON LISTENER
        view.findViewById<Button>(R.id.EditSaveButton).setOnClickListener {
            //TODO: Dodaj zapis Przepisu
            Log.d("btnSetup", "Saved")

            val mainPage = childFragmentManager.findFragmentById(R.id.EditViewMain)!! as FragmentRecipeEditMain
            val ingredientsPage = childFragmentManager.findFragmentById(R.id.EditViewIngredients)!! as FragmentRecipeEditIngredients
            val stepsPage = childFragmentManager.findFragmentById(R.id.EditViewSteps)!! as FragmentRecipeEditSteps

            var name = mainPage.requireView().findViewById<EditText>(R.id.editName).text.toString()

            //var name = view.findViewById<EditText>(R.id.editName).text.toString()
            var category = when(mainPage.requireView().findViewById<Spinner>(R.id.editCategory).selectedItem.toString()){
                "Breakfast" -> RecipeCategory.BREAKFAST
                "Lunch" -> RecipeCategory.LUNCH
                "Supper" -> RecipeCategory.SUPPER
                "Dessert" -> RecipeCategory.DESSERT
                else -> RecipeCategory.BREAKFAST
            }
            var description = mainPage.requireView().findViewById<EditText>(R.id.editDescription).text.toString()

            var ingredientsList = ingredientsPage.requireView().findViewById<RecyclerView>(R.id.EditIngredientsList)
            var ingredients: List<String> = (ingredientsList.adapter as EditTextBoxListAdapter).getItemList()

            var stepsList = stepsPage.requireView().findViewById<RecyclerView>(R.id.EditStepsList)
            var steps: List<String> = (stepsList.adapter as EditTextBoxListAdapter).getItemList()

            //Temporary static add
            LocalRecipes.InsertRecipe(
                Recipe(
                    UUID.randomUUID(),
                    "test",
                    Calendar.getInstance().getTime(),
                    name,
                    category,
                    listOf(),
                    ingredients,
                    description,
                    steps
                )
            )

        }

        return view
    }

}