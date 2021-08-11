package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.databinding.ItemEditTextboxBinding
import com.teambear.goodielist.interfaces.IEditTextChangedListener

class EditTextBoxListAdapter () : RecyclerView.Adapter<EditTextBoxListAdapter.ViewHolder>(),
    IEditTextChangedListener {

    var itemList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEditTextboxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            this
        )
    }

    override fun OnTextChanged(position: Int, newText: String) {
        itemList[position] = newText
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("{$position} / {${itemList.size}}")

        holder.deleteButton.setOnClickListener {
            itemList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.itemText.setText(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(binding: ItemEditTextboxBinding,
                            val listener: IEditTextChangedListener
                           ) : RecyclerView.ViewHolder(binding.root)
    {
        init {
            println("Create view holder")
            binding.editItemTextbox.doAfterTextChanged {
                listener.OnTextChanged(absoluteAdapterPosition, it.toString())
            }
        }

        public val itemText: EditText = binding.editItemTextbox
        public val deleteButton: ImageButton = binding.editItemDelete
    }

    public fun addNewItem(){
        itemList.add("")
    }

}