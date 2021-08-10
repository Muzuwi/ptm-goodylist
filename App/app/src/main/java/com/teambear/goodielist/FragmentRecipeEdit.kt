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

        //CHANGE SPINNER LISTENER
        view.findViewById<Spinner>(R.id.EditCategory).setOnItemSelectedListener(object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                var icon = when(position){
                    0 -> R.drawable.icon_breakfest
                    1 -> R.drawable.icon_lunch
                    2 -> R.drawable.icon_supper
                    3 -> R.drawable.icon_dessert
                    else -> R.drawable.icon_breakfest
                }
                view.findViewById<ImageView>(R.id.EditIcon).setImageResource(icon)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                view.findViewById<ImageView>(R.id.EditIcon).setImageResource(R.drawable.icon_breakfest)
            }
        })

        //SAVE BUTTON LISTENER
        view.findViewById<Button>(R.id.EditSaveButton).setOnClickListener {
            //TODO: Dodaj zapis Przepisu
            Log.d("btnSetup", "Saved")

            var name = view.findViewById<EditText>(R.id.EditName).text.toString()
            var category = when(view.findViewById<Spinner>(R.id.EditCategory).selectedItem.toString()){
                "Breakfast" -> RecipeCategory.BREAKFAST
                "Lunch" -> RecipeCategory.LUNCH
                "Supper" -> RecipeCategory.SUPPER
                "Dessert" -> RecipeCategory.DESSERT
                else -> RecipeCategory.BREAKFAST
            }
            var description = view.findViewById<EditText>(R.id.EditDescription).text.toString()

            //Temporary static add
            LocalRecipes.InsertRecipe(
                Recipe(
                    UUID.randomUUID(),
                    "test",
                    Calendar.getInstance().getTime(),
                    name,
                    category,
                    listOf(),
                    listOf(),
                    description,
                    listOf()
                )
            )

        }

        var ingridientsList = view.findViewById<RecyclerView>(R.id.EditIngredientsList)

        // Set the adapter
        if (ingridientsList is RecyclerView) {
            with(ingridientsList) {
                layoutManager = LinearLayoutManager(context)
                adapter = EditTextBoxListAdapter()
            }
        }

        view.findViewById<ImageButton>(R.id.EditIngredientAddButton).setOnClickListener {
            (ingridientsList.adapter as EditTextBoxListAdapter).addNewItem()
            ingridientsList.adapter?.notifyDataSetChanged()
        }

        var stepsList = view.findViewById<RecyclerView>(R.id.EditStepsList)

        // Set the adapter
        if (stepsList is RecyclerView) {
            with(stepsList) {
                layoutManager = LinearLayoutManager(context)
                adapter = EditTextBoxListAdapter()
            }
        }

        view.findViewById<ImageButton>(R.id.EditStepAddButton).setOnClickListener {
            (stepsList.adapter as EditTextBoxListAdapter).addNewItem()
            stepsList.adapter?.notifyDataSetChanged()
        }

        return view
    }

}