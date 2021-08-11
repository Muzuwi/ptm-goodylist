package com.teambear.goodielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.R
import com.teambear.goodielist.adapters.EditTextBoxListAdapter

class FragmentRecipeEditSteps : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recipe_edit_steps, container, false)
        var stepsList = view.findViewById<RecyclerView>(R.id.EditStepsList)

        // Set the adapter
        if (stepsList is RecyclerView) {
            with(stepsList) {
                layoutManager = LinearLayoutManager(context)
                adapter = EditTextBoxListAdapter()
            }
        }

        view.findViewById<ImageButton>(R.id.editStepAddButton).setOnClickListener {
            (stepsList.adapter as EditTextBoxListAdapter).addNewItem()
            stepsList.adapter?.notifyDataSetChanged()
        }

        return view
    }
}