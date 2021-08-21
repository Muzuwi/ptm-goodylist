package com.teambear.goodielist.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.teambear.goodielist.IconBuilder

import com.teambear.goodielist.databinding.FragmentRecipeListItemBinding
import com.teambear.goodielist.interfaces.IRecipeClickListener
import com.teambear.goodielist.interfaces.IRecipeListViewer
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
        val itemContainer: View = binding.root.rootView

        init {
            itemView.isClickable = true
            itemView.isLongClickable = true
        }

        fun BindFromRecipe(recipe: Recipe) {
            nameView.text = recipe.name
            userView.text = "Created by " + recipe.username

            val dateTime = SimpleDateFormat.getDateTimeInstance()
            dateView.text = dateTime.format(Date(recipe.created * 1000L))

            val icon = IconBuilder.getIconId(recipe.tags)
            iconView.setImageResource(icon)
        }

        fun BindListener(recipeClickListener: IRecipeClickListener, position: Int) {
            val recipe: Recipe = recipeViewer.GetItemByPostion(position) ?: return

            itemView.setOnClickListener {
                recipeClickListener.OnRecipeClick(recipe)
            }

            itemView.setOnLongClickListener {
                recipeClickListener.OnRecipeLongClick(it, recipe)
                return@setOnLongClickListener true
            }
        }
   }

}