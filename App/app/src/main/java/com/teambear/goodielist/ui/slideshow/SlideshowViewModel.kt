package com.teambear.goodielist.ui.slideshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {
    var text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
}