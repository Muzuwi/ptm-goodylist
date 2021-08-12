package com.teambear.goodielist

import android.os.Bundle
import android.os.ParcelUuid
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.adapters.RecipeListAdapter
import com.teambear.goodielist.models.ParcelRecipe
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.storage.LocalRecipes
import com.teambear.goodielist.workers.DummyRecipeListViewer

/**
 * A fragment representing a list of Items.
 */
class RecipeListFragment : Fragment(), IRecipeClickListener {
    private var columnCount = 1
    private var listViewer: DummyRecipeListViewer = DummyRecipeListViewer(LocalRecipes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list_layout, container, false)
        val listener = this
        listViewer = DummyRecipeListViewer(LocalRecipes)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RecipeListAdapter(listViewer, listener)
            }
        }

        return view
    }

    override fun OnRecipeClick(recipe: Recipe) {
        System.out.println("Clicked on recipe id=" + recipe.id.toString());
        val nav = findNavController()
        val recipeListFragmentToRecipeViewFragment =
            HomeFragmentDirections.recipeListFragmentToRecipeViewFragment(ParcelRecipe(recipe))
        nav.navigate(recipeListFragmentToRecipeViewFragment);
    }

    override fun OnRecipeLongClick(view: View, recipe: Recipe) {
        val pop = PopupMenu(requireContext(), view)
        pop.inflate(R.menu.recipe_context)
        pop.setOnMenuItemClickListener {
            if(it.itemId == R.id.menuRecipeEdit) {
                System.out.println("Editing recipe id=" + recipe.id.toString());
                val nav = findNavController()
                val recipeListFragmentToRecipeViewFragment =
                    HomeFragmentDirections.recipeListFragmentToFragmentRecipeEdit()
                        .setId(ParcelUuid(recipe.id))
                nav.navigate(recipeListFragmentToRecipeViewFragment)
            } else if(it.itemId == R.id.menuRecipeDelete) {
                val ret = LocalRecipes.DeleteRecipe(recipe.id)
                if(ret) {
                    Snackbar.make(requireView(), "Recipe deleted", Snackbar.LENGTH_SHORT).show()

                    listViewer = DummyRecipeListViewer(LocalRecipes)
                    (requireView() as RecyclerView).adapter = RecipeListAdapter(listViewer, this)
                    ((requireView() as RecyclerView).adapter as RecipeListAdapter).notifyDataSetChanged()
                } else {
                    Snackbar.make(requireView(), "Could not delete recipe", Snackbar.LENGTH_SHORT).show()
                }
            }

            return@setOnMenuItemClickListener true
        }

        pop.show()
    }

}