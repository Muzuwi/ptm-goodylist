package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.databinding.ItemDisplayTagBinding
import com.teambear.goodielist.databinding.ItemEditTextboxBinding
import com.teambear.goodielist.interfaces.IEditTextChangedListener

class DisplayTagListAdapter () : RecyclerView.Adapter<DisplayTagListAdapter.ViewHolder>(),
    IEditTextChangedListener {

    private var itemList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDisplayTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun OnTextChanged(position: Int, newText: String) {
        itemList[position] = newText
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemText.setText(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
    fun getItemList(): List<String> = itemList
    fun setItemList(toDisplay: List<String>) {
        itemList = toDisplay.toMutableList()
    }

    inner class ViewHolder(
        binding: ItemDisplayTagBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {

        public val itemText: TextView = binding.displayTagString
    }

    public fun addNewItem(){
        itemList.add("")
    }

}