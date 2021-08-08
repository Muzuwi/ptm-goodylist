package com.example.ptm_goodies_klient.ui.recipies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ptm_goodies_klient.R

class RecipiesFragment : Fragment() {

    private lateinit var recipiesViewModel: RecipiesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recipiesViewModel =
                ViewModelProvider(this).get(RecipiesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        recipiesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}