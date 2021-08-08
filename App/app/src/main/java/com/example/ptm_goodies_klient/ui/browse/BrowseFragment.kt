package com.example.ptm_goodies_klient.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ptm_goodies_klient.R

class BrowseFragment : Fragment() {

    private lateinit var browseViewModel: BrowseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        browseViewModel =
                ViewModelProvider(this).get(BrowseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_browse, container, false)
        return root
    }
}