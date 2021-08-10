package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.databinding.ItemEditTextboxBinding

class EditTextBoxListAdapter () : RecyclerView.Adapter<EditTextBoxListAdapter.ViewHolder>() {

    private var itemList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEditTextboxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("{$position} / {${itemList.size}}")

        holder.itemText.doAfterTextChanged {
            itemList[position] = it.toString()
        }

        holder.deleteButton.setOnClickListener {
            itemList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.itemText.setText(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(binding: ItemEditTextboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        public val itemText: EditText = binding.editItemTextbox
        public val deleteButton: ImageButton = binding.editItemDelete
    }

    public fun addNewItem(){
        itemList.add("")
    }

}