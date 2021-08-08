package com.example.ptm_goodies_klient.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ptm_goodies_klient.MyRecipeRecyclerViewAdapter
import com.example.ptm_goodies_klient.R
import com.example.ptm_goodies_klient.data.Recipes

/**
 * A fragment representing a list of Items.
 */
class RecipeFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyRecipeRecyclerViewAdapter(Recipes.ITEMS)
            }
        }
        return view
    }

}