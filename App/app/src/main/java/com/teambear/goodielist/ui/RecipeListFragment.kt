package com.teambear.goodielist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teambear.goodielist.R
import com.teambear.goodielist.struct.Category
import com.teambear.goodielist.struct.Recipe

/**
 * A fragment representing a list of Items.
 */
class RecipeListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list_layout, container, false)

        val items = listOf<Recipe>(
            Recipe(0, "Costam", Category.BREAKFAST, listOf(), listOf(), "Pyszne śniadanie", listOf()),
            Recipe(1, "Obiadek", Category.LUNCH, listOf(), listOf(), "Pyszny obiadek", listOf()),
        )

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RecipeListAdapter(items)
            }
        }

        return view
    }

}