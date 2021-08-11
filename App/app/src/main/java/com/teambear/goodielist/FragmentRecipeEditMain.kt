package com.teambear.goodielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.adapters.EditTagListAdapter
import com.teambear.goodielist.dialogs.editAddTagDialog

class FragmentRecipeEditMain : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recipe_edit_main, container, false)

        val tagList = view.findViewById<RecyclerView>(R.id.editTagList)
        // Set the adapter
        with(tagList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = EditTagListAdapter()
        }

        view.findViewById<ImageButton>(R.id.editTagAddButton).setOnClickListener {
            var dialog = editAddTagDialog(tagList)
            dialog.show(parentFragmentManager, "Hello")
        }

        return view
    }
}