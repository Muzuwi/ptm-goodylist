package com.teambear.goodielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.R
import com.teambear.goodielist.adapters.EditTextBoxListAdapter

class FragmentRecipeEditIngredients : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recipe_edit_ingredients, container, false)
        var ingredientsList = view.findViewById<RecyclerView>(R.id.EditIngredientsList)

        println("Ingredients fragment created")

        // Set the adapter
        if (ingredientsList is RecyclerView) {
            with(ingredientsList) {
                layoutManager = LinearLayoutManager(context)
                adapter = EditTextBoxListAdapter()
            }
        }

        view.findViewById<ImageButton>(R.id.editIngredientAddButton).setOnClickListener {
            (ingredientsList.adapter as EditTextBoxListAdapter).addNewItem()
            ingredientsList.adapter?.notifyDataSetChanged()
        }

        return view
    }
}