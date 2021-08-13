package com.teambear.goodielist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.storage.LocalRecipes
import java.util.*
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.teambear.goodielist.adapters.EditPageAdapter
import com.teambear.goodielist.adapters.EditTagListAdapter
import com.teambear.goodielist.adapters.EditTextBoxListAdapter
import com.teambear.goodielist.interfaces.IRecipeEditViewCreated
import com.teambear.goodielist.network.UserAccount


class FragmentRecipeEdit : Fragment(), IRecipeEditViewCreated {
    private lateinit var editPageAdapter: EditPageAdapter
    private lateinit var viewPager: ViewPager
    private val args: FragmentRecipeEditArgs by navArgs()
    private var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_recipe_edit, container, false)

        editPageAdapter = EditPageAdapter(childFragmentManager, this)
        viewPager = view.findViewById(R.id.EditViewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = editPageAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.editTabLayout)
        tabLayout.setupWithViewPager(viewPager)



        //SAVE BUTTON LISTENER
        view.findViewById<Button>(R.id.EditSaveButton).setOnClickListener {
            val recipe = createRecipeFromFragment()
            if(args.id == null) {
                LocalRecipes.InsertRecipe(recipe)
            } else {
                LocalRecipes.UpdateRecipe(args.id!!.uuid, recipe)
            }

            requireView().findNavController().popBackStack(R.id.recipeListFragment, false)
        }

        if(args.id != null) {
            recipe = LocalRecipes.GetRecipeByUUID(args.id!!.uuid)
        }


        return view
    }

    private fun createRecipeFromFragment(): Recipe {
        val adapter = editPageAdapter.pageFragments
        val mainPage = adapter[0] as FragmentRecipeEditMain
        val ingredientsPage = adapter[1] as FragmentRecipeEditIngredients
        val stepsPage = adapter[2] as FragmentRecipeEditSteps

        val name = mainPage.requireView().findViewById<EditText>(R.id.editName).text.toString()
        val description = mainPage.requireView().findViewById<EditText>(R.id.editDescription).text.toString()

        val tagList = mainPage.requireView().findViewById<RecyclerView>(R.id.editTagList)
        val tags: List<String> = (tagList.adapter as EditTagListAdapter).itemList

        val ingredientsList = ingredientsPage.requireView().findViewById<RecyclerView>(R.id.EditIngredientsList)
        val ingredients: List<String> = (ingredientsList.adapter as EditTextBoxListAdapter).itemList

        val stepsList = stepsPage.requireView().findViewById<RecyclerView>(R.id.EditStepsList)
        val steps: List<String> = (stepsList.adapter as EditTextBoxListAdapter).itemList

        val username: String = UserAccount.GetLocalUser()!!.username

        val uuid: UUID
        if(args.id == null) {
            uuid = UUID.randomUUID()
        } else {
            uuid = args.id!!.uuid
        }

        return Recipe(
            uuid,
            username,
            Calendar.getInstance().time,
            name,
            RecipeCategory.BREAKFAST,
            tags,
            ingredients,
            description,
            steps
        )
    }

    override fun OnTabViewCreated(source: Fragment) {
        if (recipe == null) {
            return
        }

        val adapter = editPageAdapter.pageFragments

        if(source is FragmentRecipeEditMain) {
            val mainPage = adapter[0] as FragmentRecipeEditMain
            mainPage.requireView().findViewById<EditText>(R.id.editName).setText(recipe!!.name)
            mainPage.requireView().findViewById<EditText>(R.id.editDescription).setText(recipe!!.description)
            val tagList = mainPage.requireView().findViewById<RecyclerView>(R.id.editTagList)
            (tagList.adapter as EditTagListAdapter).itemList = recipe!!.tags as MutableList<String>
        }
        else if(source is FragmentRecipeEditSteps) {
            val stepsPage = adapter[2] as FragmentRecipeEditSteps
            val stepsList = stepsPage.requireView().findViewById<RecyclerView>(R.id.EditStepsList)
            (stepsList.adapter as EditTextBoxListAdapter).itemList = recipe!!.steps as MutableList<String>

        }
        else if(source is FragmentRecipeEditIngredients) {
            val ingredientsPage = adapter[1] as FragmentRecipeEditIngredients
            val ingredientsList = ingredientsPage.requireView().findViewById<RecyclerView>(R.id.EditIngredientsList)
            (ingredientsList.adapter as EditTextBoxListAdapter).itemList = recipe!!.ingredients as MutableList<String>
        }
    }

}
