package com.teambear.goodielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment

class FragmentRecipeEditMain : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_recipe_edit_main, container, false)

        //CHANGE SPINNER LISTENER
        view.findViewById<Spinner>(R.id.editCategory).setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                var icon = when(position){
                    0 -> R.drawable.icon_breakfest
                    1 -> R.drawable.icon_lunch
                    2 -> R.drawable.icon_supper
                    3 -> R.drawable.icon_dessert
                    else -> R.drawable.icon_breakfest
                }
                view.findViewById<ImageView>(R.id.editIcon).setImageResource(icon)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                view.findViewById<ImageView>(R.id.editIcon).setImageResource(R.drawable.icon_breakfest)
            }
        })

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}