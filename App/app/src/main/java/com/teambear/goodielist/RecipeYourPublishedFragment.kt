package com.teambear.goodielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.teambear.goodielist.adapters.RecipeListAdapter
import com.teambear.goodielist.databinding.FragmentRecipeOnlineListBinding
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.interfaces.IRecipeListViewer
import com.teambear.goodielist.models.ParcelRecipe
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.network.GoodieAPIWorker
import com.teambear.goodielist.network.UserAccount
import com.teambear.goodielist.storage.LocalRecipes
import com.teambear.goodielist.workers.DummyRecipeListViewer
import kotlinx.coroutines.launch

class RecipeYourPublishedFragment : Fragment(), IRecipeClickListener, IRecipeListViewer {
    private lateinit var binding: FragmentRecipeOnlineListBinding
    private var recipes: List<Recipe> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_online_list, container, false)
        binding = FragmentRecipeOnlineListBinding.bind(view)

        binding.noRecipesOnlineText.isVisible = false
        binding.onlineRecipeList.layoutManager = LinearLayoutManager(view.context)
        binding.onlineRecipeListContainer.isRefreshing = true
        binding.onlineRecipeListContainer.setOnRefreshListener {
            lifecycleScope.launch {
                FetchRecipes()
            }
        }

        lifecycleScope.launch {
            FetchRecipes()
        }


        return view
    }

    private suspend fun FetchRecipes() {
        val user = UserAccount.GetLocalUser() ?: return
        user.token ?: run {
            binding.onlineRecipeListContainer.isRefreshing = false
            Snackbar.make(requireView(), "You are currently in offline mode. Please log in to access online recipes.", Snackbar.LENGTH_SHORT).show()
            return
        }

        val recipes = GoodieAPIWorker.FetchUserRecipes(user.token, user.username)
        recipes ?: run {
            binding.onlineRecipeListContainer.isRefreshing = false
            binding.noRecipesOnlineText.isVisible = true
            return
        }

        this.recipes = recipes
        println("Recipe length: ${recipes.size}")
        for(recipe in recipes) {
            println(recipe.toString())
        }

        binding.onlineRecipeList.adapter = RecipeListAdapter(this, this)
        binding.onlineRecipeList.adapter!!.notifyDataSetChanged()
        binding.onlineRecipeListContainer.isRefreshing = false
        binding.noRecipesOnlineText.isVisible = false
    }

    override fun OnRecipeClick(recipe: Recipe) {
        val nav = findNavController()
        val dirs = RecipeYourPublishedFragmentDirections
            .actionRecipeYourPublishedFragmentToRecipeViewFragment(ParcelRecipe(recipe))
        nav.navigate(dirs)
    }

    override fun OnRecipeLongClick(view: View, recipe: Recipe) {
        Snackbar.make(view, "Deleting recipe..", Snackbar.LENGTH_SHORT).show()
        lifecycleScope.launch {
            val user = UserAccount.GetLocalUser() ?: return@launch
            user.token ?: return@launch

            val ret = GoodieAPIWorker.DeleteRecipe(user.token, recipe.id)
            if(ret) {
                Snackbar.make(view, "Recipe removed successfully", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, "Failed to remove recipe", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun GetItemCount(): Int {
        return recipes.size
    }

    override fun GetItemByPostion(position: Int): Recipe? {
        return recipes[position]
    }
}