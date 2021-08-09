package com.teambear.goodielist.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.teambear.goodielist.R

import com.teambear.goodielist.databinding.FragmentRecipeListItemBinding
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.interfaces.IRecipeListViewer
import com.teambear.goodielist.interfaces.IUserRecipeWorker
import com.teambear.goodielist.models.Recipe
import java.text.SimpleDateFormat
import java.util.*


class RecipeListAdapter(
    private val recipeViewer: IRecipeListViewer,
    private val recipeClickListener: IRecipeClickListener
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentRecipeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Recipe = recipeViewer.GetItemByPostion(position) ?: return

        holder.BindFromRecipe(item);
        holder.BindListener(recipeClickListener, position)
    }

    override fun getItemCount(): Int = recipeViewer.GetItemCount()

    inner class ViewHolder(binding: FragmentRecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameView: TextView = binding.ListItemName
        private val userView: TextView = binding.ListItemUser
        private val dateView: TextView = binding.ListItemDate
        private val iconView: ImageView = binding.ListItemIcon
        private val itemContainer: View = binding.root.rootView

        fun BindFromRecipe(recipe: Recipe) {
            nameView.text = recipe.name
            userView.text = recipe.username

            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            dateView.text = simpleDateFormat.format(recipe.created)

            val icon = when(recipe.category){
                RecipeCategory.BREAKFAST -> R.drawable.icon_breakfest
                RecipeCategory.LUNCH -> R.drawable.icon_lunch
                RecipeCategory.SUPPER -> R.drawable.icon_supper
                RecipeCategory.DESSERT -> R.drawable.icon_dessert
            }
            iconView.setImageResource(icon)
        }

        fun BindListener(recipeClickListener: IRecipeClickListener, position: Int) {
            val recipe: Recipe = recipeViewer.GetItemByPostion(position) ?: return

            itemContainer.setOnClickListener {
                recipeClickListener.OnRecipeClick(recipe)
            }
        }
   }

}