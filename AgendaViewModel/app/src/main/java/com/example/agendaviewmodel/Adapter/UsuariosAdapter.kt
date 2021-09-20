package com.example.agendaviewmodel.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaviewmodel.Adapter.usuariosViewHolder.UsuariosViewHolder
import com.example.agendaviewmodel.R
import com.example.agendaviewmodel.clases.UsuarioAgenda

class UsuariosAdapter: RecyclerView.Adapter<UsuariosViewHolder>() {

    var items : MutableList<UsuarioAgenda> = ArrayList()

    fun Usuario( items: MutableList<UsuarioAgenda>){
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuariosViewHolder(layoutInflater.inflate(R.layout.row_usuario, parent, false))
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}