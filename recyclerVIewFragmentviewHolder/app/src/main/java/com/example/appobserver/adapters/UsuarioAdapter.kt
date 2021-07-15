package com.example.appobserver.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appobserver.R
import com.example.appobserver.adapters.ViewHolder.UsuarioViewHolder
import com.example.appobserver.clases.usuario

class UsuarioAdapter : RecyclerView.Adapter<UsuarioViewHolder>() {
    var items : MutableList<usuario> = ArrayList()
    lateinit var context : Context

    fun UsuarioAdapter(items : MutableList<usuario>, context: Context){
        this.items = items
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.dt_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


}