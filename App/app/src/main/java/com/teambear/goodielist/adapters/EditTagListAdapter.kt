package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.databinding.ItemEditTagBinding

class EditTagListAdapter () : RecyclerView.Adapter<EditTagListAdapter.ViewHolder>() {

    var itemList: MutableList<String> = mutableListOf("Dummy","Dummy2","Dummy3","Dummy4","Dummy5")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEditTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.deleteButton.setOnClickListener {
            itemList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.itemText.setText(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(binding: ItemEditTagBinding,
    ) : RecyclerView.ViewHolder(binding.root)
    {
        public val itemText: TextView = binding.editTagString
        public val deleteButton: ImageButton = binding.tagDeleteButton
    }

    public fun addNewItem(newTag: String){
        itemList.add(newTag)
    }

}