package com.teambear.goodielist.ui.lists

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.teambear.goodielist.R

import com.teambear.goodielist.databinding.FragmentRecipeListItemBinding
import com.teambear.goodielist.struct.Category
import com.teambear.goodielist.struct.Recipe
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class RecipeListAdapter(
    private val values: List<Recipe>,
    private val recipeClickListener: RecipeClickListener
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
        val item = values[position]

        holder.nameView.text = item.name
        holder.userView.text = item.username

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        holder.dateView.text = simpleDateFormat.format(item.created)

        val icon = when(item.category){
            Category.BREAKFAST -> R.drawable.icon_breakfest
            Category.LUNCH -> R.drawable.icon_lunch
            Category.SUPPER -> R.drawable.icon_supper
            Category.DESSERT -> R.drawable.icon_dessert
        }
        holder.iconView.setImageResource(icon)

        holder.itemContainer.setOnClickListener {
            recipeClickListener.OnRecipeClick(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentRecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.ListItemName
        val userView: TextView = binding.ListItemUser
        val dateView: TextView = binding.ListItemDate
        val iconView: ImageView = binding.ListItemIcon
        val itemContainer: View = binding.root.rootView
    }

}