package com.example.viewmodelrecycler.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class numeroViewModel: ViewModel(){


    private val numeroVM : MutableLiveData<MutableList<Int>> = MutableLiveData()


    init {

    }

    fun setNumeroVM (numero : MutableList<Int>){
        numeroVM.value = numero
    }

    fun getNumeroVM() : LiveData<MutableList<Int>>{
        return numeroVM
    }
}