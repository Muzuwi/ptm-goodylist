package com.teambear.goodielist

import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.teambear.goodielist.R
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.storage.LocalRecipes
import java.text.SimpleDateFormat

class RecipeViewFragment : Fragment() {
    val args: RecipeViewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = LocalRecipes.GetRecipeByUUID(args.id.uuid) ?: return

        view.findViewById<TextView>(R.id.DetailsName).text = recipe.name
        view.findViewById<TextView>(R.id.DetailsAuthor).text = recipe.username
        view.findViewById<TextView>(R.id.DetailsCategory).text = recipe.category.toString()
        view.findViewById<TextView>(R.id.DetailsDescription).text = recipe.description

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        view.findViewById<TextView>(R.id.DetailsCreated).text = simpleDateFormat.format(recipe.created)

        val icon = when(recipe.category){
            RecipeCategory.BREAKFAST -> R.drawable.icon_breakfest
            RecipeCategory.LUNCH -> R.drawable.icon_lunch
            RecipeCategory.SUPPER -> R.drawable.icon_supper
            RecipeCategory.DESSERT -> R.drawable.icon_dessert
        }
        view.findViewById<ImageView>(R.id.DetailsIcon).setImageResource(icon)

        var ingredientsListString = "Ingredients:\n"
        recipe.ingredients.forEach {
            ingredientsListString += "-> ${it.toString()}\n"
        }
        view.findViewById<TextView>(R.id.DetailsIngredients).text = ingredientsListString

        var stepsListString = "Steps:\n"
        recipe.steps.forEach {
            stepsListString += "-> ${it.toString()}\n"
        }
        view.findViewById<TextView>(R.id.DetailsSteps).text = stepsListString
    }

}