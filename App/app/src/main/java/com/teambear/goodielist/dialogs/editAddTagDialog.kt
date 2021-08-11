package com.teambear.goodielist.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.teambear.goodielist.R
import com.teambear.goodielist.adapters.EditTagListAdapter

class editAddTagDialog(val tagList: RecyclerView) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.dialog_edit_add_tag,null);

            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setView(view)
            builder.setMessage("Hello")
                .setPositiveButton("Add",
                    DialogInterface.OnClickListener { dialog, id ->
                        var newTag = view.findViewById<EditText>(R.id.editDialogNewTag).text.toString()
                        (tagList.adapter as EditTagListAdapter).addNewItem(newTag)
                        (tagList.adapter as EditTagListAdapter).notifyDataSetChanged()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}