package com.example.appobserver.ws

import com.example.appobserver.clases.usuario
import java.util.*
import kotlin.collections.ArrayList

class bd {

    private var usuarios : MutableList<usuario> = ArrayList()

    fun setUsuarios(usuario:usuario){
        usuarios.add(usuario)
    }

    fun getUsuario() : MutableList<usuario>{
        return usuarios
    }

}