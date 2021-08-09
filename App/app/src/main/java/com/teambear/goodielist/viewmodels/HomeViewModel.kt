package com.teambear.goodielist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
}