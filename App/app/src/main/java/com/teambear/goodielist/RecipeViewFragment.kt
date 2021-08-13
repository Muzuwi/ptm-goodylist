package com.teambear.goodielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.adapters.DisplayTagListAdapter
import com.teambear.goodielist.adapters.DisplayTextViewListAdapter
import com.teambear.goodielist.models.RecipeCategory
import java.text.SimpleDateFormat
import android.text.method.ScrollingMovementMethod
import com.teambear.goodielist.models.ParcelRecipe


class RecipeViewFragment : Fragment() {
    val args: RecipeViewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recipe_view, container, false)

        //Tag List - Adapter
        val tagList = view.findViewById<RecyclerView>(R.id.detailsTagList)
        with(tagList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = DisplayTagListAdapter()
        }

        //Ingredients List - Adapter
        val ingredientsList = view.findViewById<RecyclerView>(R.id.detailsIngredientList)
        with(ingredientsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = DisplayTextViewListAdapter()
        }

        //Steps List - Adapter
        val stepsList = view.findViewById<RecyclerView>(R.id.detailsStepsList)
        with(stepsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = DisplayTextViewListAdapter()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = ParcelRecipe.deparcelize(args.recipe)

        //Recipe name
        view.findViewById<TextView>(R.id.DetailsName).text = recipe.name

        //Recipe author
        view.findViewById<TextView>(R.id.DetailsAuthor).text = recipe.username

        //Recipe description
        view.findViewById<TextView>(R.id.DetailsDescription).text = recipe.description
        view.findViewById<TextView>(R.id.DetailsDescription).movementMethod = ScrollingMovementMethod()


        //Recipe created date
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        view.findViewById<TextView>(R.id.DetailsCreated).text = simpleDateFormat.format(recipe.created)

        //List of tags
        val tagList = view.findViewById<RecyclerView>(R.id.detailsTagList)
        (tagList.adapter as DisplayTagListAdapter).setItemList(recipe.tags)

        //Icon
        //TODO: Uzaleznij od tagów nie kategorii
        val icon = when(recipe.category){
            RecipeCategory.BREAKFAST -> R.drawable.icon_breakfest
            RecipeCategory.LUNCH -> R.drawable.icon_lunch
            RecipeCategory.SUPPER -> R.drawable.icon_supper
            RecipeCategory.DESSERT -> R.drawable.icon_dessert_old
        }
        view.findViewById<ImageView>(R.id.DetailsIcon).setImageResource(icon)

        //Ingredients
        val ingredientsList = view.findViewById<RecyclerView>(R.id.detailsIngredientList)
        (ingredientsList.adapter as DisplayTextViewListAdapter).setItemList(recipe.ingredients)

        //Steps
        val stepsList = view.findViewById<RecyclerView>(R.id.detailsStepsList)
        (stepsList.adapter as DisplayTextViewListAdapter).setItemList(recipe.steps)
    }

}