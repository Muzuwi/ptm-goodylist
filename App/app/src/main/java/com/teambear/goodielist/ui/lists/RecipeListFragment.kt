package com.teambear.goodielist.ui.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.teambear.goodielist.R
import com.teambear.goodielist.struct.Category
import com.teambear.goodielist.struct.Recipe
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class RecipeListFragment : Fragment(), RecipeClickListener {

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
            Recipe("test", 0, Calendar.getInstance().getTime(),"Costam", Category.BREAKFAST, listOf(), listOf(), "Pyszne śniadanie", listOf()),
            Recipe("test", 1, Calendar.getInstance().getTime(),"Obiadek", Category.LUNCH, listOf(), listOf(), "Pyszny obiadek", listOf()),
        )

        val listener = this

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RecipeListAdapter(items, listener)
            }
        }

        return view
    }

    override fun OnRecipeClick(position: Int) {
        val nav = findNavController()
        nav.navigate(R.id.action_nav_home_to_recipeViewFragment2);
    }

    override fun OnRecipeLongClick(position: Int) {
        TODO("Not yet implemented")
    }

}