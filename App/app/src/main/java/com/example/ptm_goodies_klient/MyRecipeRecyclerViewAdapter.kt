package com.example.ptm_goodies_klient

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.ptm_goodies_klient.data.Recipes.Recipe

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRecipeRecyclerViewAdapter(
    private val values: List<Recipe>
) : RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val itemContainer: View = view.rootView

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}