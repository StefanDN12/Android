package com.example.agendaviewmodel.Adapter.usuariosViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaviewmodel.R
import com.example.agendaviewmodel.clases.UsuarioAgenda

class UsuariosViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val panel : LinearLayout = view.findViewById(R.id.panel)
    val nombre : TextView = view.findViewById(R.id.nombre)
    val apellido : TextView = view.findViewById(R.id.apellido)
    val telefono : TextView = view.findViewById(R.id.telefono)

    fun bind(item: UsuarioAgenda){
        nombre.text = item.nombre
        apellido.text = item.apellido
        telefono.text = item.numero.toString()
    }


}