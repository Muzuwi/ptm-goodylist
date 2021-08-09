package com.teambear.goodielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.adapters.RecipeListAdapter
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.workers.DummyRecipeListViewer
import com.teambear.goodielist.workers.DummyUserRecipeWorker

/**
 * A fragment representing a list of Items.
 */
class RecipeListFragment : Fragment(), IRecipeClickListener {

    private var columnCount = 1
    private val dummyUserRecipeWorker = DummyUserRecipeWorker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list_layout, container, false)

        val listener = this

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RecipeListAdapter(DummyRecipeListViewer(dummyUserRecipeWorker), listener)
            }
        }

        return view
    }

    override fun OnRecipeClick(recipe: Recipe) {
        System.out.println("Clicked on recipe id=" + recipe.id.toString());
        val nav = findNavController()
        nav.navigate(R.id.action_nav_home_to_recipeViewFragment2);
    }

    override fun OnRecipeLongClick(recipe: Recipe) {

        TODO("Not yet implemented")
    }

}