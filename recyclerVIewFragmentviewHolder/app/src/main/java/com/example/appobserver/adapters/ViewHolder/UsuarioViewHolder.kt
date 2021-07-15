package com.example.appobserver.adapters.ViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appobserver.R
import com.example.appobserver.clases.usuario

class UsuarioViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val nombre = view.findViewById<TextView>(R.id.txtNombre)
    val apellido = view.findViewById<TextView>(R.id.txtApellido)
    val fecha = view.findViewById<TextView>(R.id.txtFecha)


    fun bind (item : usuario){
        nombre.text = item.nombre
        apellido.text = item.apellido
        fecha.text = item.fechaInsert
    }
}