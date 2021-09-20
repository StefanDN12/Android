package com.example.brastlewark.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brastlewark.GnomeClass

class gnomeViewModel:ViewModel() {
    val gnomeModel = MutableLiveData<GnomeClass>()

    fun onCreate(){

    }
}