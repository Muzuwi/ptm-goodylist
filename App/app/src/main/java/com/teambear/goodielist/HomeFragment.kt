package com.teambear.goodielist

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.ParcelUuid
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import com.teambear.goodielist.network.GoodieAPIWorker
import com.teambear.goodielist.network.UserAccount
import com.teambear.goodielist.storage.LocalRecipes
import com.teambear.goodielist.viewmodels.HomeViewModel
import com.teambear.goodielist.workers.DummyRecipeListViewer
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), IRecipeClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var columnCount = 1
    private var listViewer: DummyRecipeListViewer = DummyRecipeListViewer(LocalRecipes, null)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val listener = this
        listViewer = DummyRecipeListViewer(LocalRecipes, null)

        // Set the adapter
        with(view.findViewById<RecyclerView>(R.id.homeRecipeList)) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = RecipeListAdapter(listViewer, listener)
        }

        view.findViewById<EditText>(R.id.homeSearchName).addTextChangedListener {


            if(view.findViewById<EditText>(R.id.homeSearchName).text.isEmpty() ){
                listViewer = DummyRecipeListViewer(LocalRecipes, null)
            }
            else {
                val searchName = view.findViewById<EditText>(R.id.homeSearchName).text.toString()
                listViewer = DummyRecipeListViewer(LocalRecipes, searchName)

            }

            // Set the adapter
            with(view.findViewById<RecyclerView>(R.id.homeRecipeList)) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = RecipeListAdapter(listViewer, listener)
            }
            view.findViewById<RecyclerView>(R.id.homeRecipeList).adapter?.notifyDataSetChanged()
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

                    listViewer = DummyRecipeListViewer(LocalRecipes, null)
                    (requireView().findViewById(R.id.homeRecipeList) as RecyclerView).adapter = RecipeListAdapter(listViewer, this)
                    ((requireView().findViewById(R.id.homeRecipeList) as RecyclerView).adapter as RecipeListAdapter).notifyDataSetChanged()
                } else {
                    Snackbar.make(requireView(), "Could not delete recipe", Snackbar.LENGTH_SHORT).show()
                }
            } else if(it.itemId == R.id.menuRecipePublish) {
                lifecycleScope.launch {
                    val user = UserAccount.GetLocalUser() ?: return@launch
                    user.token ?: return@launch

                    Snackbar.make(requireView(), "Uploading recipe..", Snackbar.LENGTH_INDEFINITE).show()

                    val ret = GoodieAPIWorker.CreateRecipe(user.token, recipe)
                    if(ret) {
                        Snackbar.make(requireView(), "Recipe published successfully", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(requireView(), "Failed to publish recipe", Snackbar.LENGTH_SHORT).show()
                    }

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