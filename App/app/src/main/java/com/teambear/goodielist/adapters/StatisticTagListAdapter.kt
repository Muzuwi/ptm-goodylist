package com.teambear.goodielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.IconBuilder
import com.teambear.goodielist.databinding.ItemDisplayTagBinding
import com.teambear.goodielist.databinding.ItemEditTextboxBinding
import com.teambear.goodielist.databinding.ItemStatisticsTagBinding
import com.teambear.goodielist.interfaces.IEditTextChangedListener

class StatisticTagListAdapter () : RecyclerView.Adapter<StatisticTagListAdapter.ViewHolder>(),
    IEditTextChangedListener {

    private var itemList: MutableList<String> = mutableListOf()
    private var quantityList: HashMap<String, Int> = hashMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStatisticsTagBinding.inflate(
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
        holder.tagStr.setText(itemList[position])
        val qty = quantityList[itemList[position]]
        holder.tagQty.setText(qty.toString())
        holder.tagIcon.setImageResource(IconBuilder.getIconId(itemList[position]))
    }

    override fun getItemCount(): Int = itemList.size

    fun setItemList(toDisplay: HashMap<String, Int>) {
        itemList = toDisplay.keys.toMutableList()
        quantityList = toDisplay
    }

    inner class ViewHolder(
        binding: ItemStatisticsTagBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {

        public val tagStr: TextView = binding.statTag
        public val tagQty: TextView = binding.statTagQty
        public val tagIcon: ImageView = binding.statsTagIcon
    }

}