package com.teambear.goodielist.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    var text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
}