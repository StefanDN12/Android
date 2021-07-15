package com.example.appobserver.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appobserver.clases.usuario

class viewModelPrueba : ViewModel() {

    private val usuariosVM : MutableLiveData<MutableList<usuario>> = MutableLiveData()

        init {

        }

    fun setUsuariosVM(usuarios : MutableList<usuario>){
        usuariosVM.value = usuarios
    }

    fun getUsuarioVM() : LiveData<MutableList<usuario>> {
        return usuariosVM
    }

}