package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.databinding.ItemDisplayTextViewBinding
import com.teambear.goodielist.databinding.ItemEditTextboxBinding
import com.teambear.goodielist.interfaces.IEditTextChangedListener

class DisplayTextViewListAdapter () : RecyclerView.Adapter<DisplayTextViewListAdapter.ViewHolder>(),
    IEditTextChangedListener {

    private var itemList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDisplayTextViewBinding.inflate(
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
        holder.itemText.setText(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun setItemList(toDisplay: List<String>) {
        itemList = toDisplay.toMutableList()
    }

    inner class ViewHolder(binding: ItemDisplayTextViewBinding,
                            val listener: IEditTextChangedListener
                           ) : RecyclerView.ViewHolder(binding.root)
    {
        public val itemText: TextView = binding.displayLine
    }

}