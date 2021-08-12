package com.teambear.goodielist

import android.os.Bundle
import android.os.ParcelUuid
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.teambear.goodielist.adapters.RecipeListAdapter
import com.teambear.goodielist.databinding.FragmentHomeBinding
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.models.ParcelRecipe
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.storage.LocalRecipes
import com.teambear.goodielist.viewmodels.HomeViewModel
import com.teambear.goodielist.workers.DummyRecipeListViewer

class HomeFragment : Fragment(), IRecipeClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var columnCount = 1
    private var listViewer: DummyRecipeListViewer = DummyRecipeListViewer(LocalRecipes)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val listener = this
        listViewer = DummyRecipeListViewer(LocalRecipes)

        // Set the adapter
        with(view.findViewById<RecyclerView>(R.id.homeRecipeList)) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = RecipeListAdapter(listViewer, listener)
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
                    (requireView().findViewById(R.id.homeRecipeList) as RecyclerView).adapter = RecipeListAdapter(listViewer, this)
                    ((requireView().findViewById(R.id.homeRecipeList) as RecyclerView).adapter as RecipeListAdapter).notifyDataSetChanged()
                } else {
                    Snackbar.make(requireView(), "Could not delete recipe", Snackbar.LENGTH_SHORT).show()
                }
            }

            return@setOnMenuItemClickListener true
        }

        pop.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}