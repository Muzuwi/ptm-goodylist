package com.teambear.goodielist.ui.lists

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.teambear.goodielist.databinding.FragmentRecipeListItemBinding
import com.teambear.goodielist.struct.Recipe


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

        holder.numberView.text = item.id.toString()
        holder.contentView.text = item.description

        holder.itemContainer.setOnClickListener {
            recipeClickListener.OnRecipeClick(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentRecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val numberView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val itemContainer: View = binding.root.rootView
    }

}