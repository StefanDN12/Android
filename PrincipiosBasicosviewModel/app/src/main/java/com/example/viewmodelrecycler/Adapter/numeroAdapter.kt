package com.example.viewmodelrecycler.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelrecycler.Adapter.viewHolder.numeroViewHolder
import com.example.viewmodelrecycler.R


import java.util.ArrayList

class numeroAdapter: RecyclerView.Adapter<numeroViewHolder>(){

    var items : MutableList<Int> = ArrayList()
    lateinit var context : Context


    fun numeroAdapter(items : MutableList<Int>, context: Context){
        this.items = items
        this.context = context
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): numeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return numeroViewHolder(layoutInflater.inflate(R.layout.dt_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: numeroViewHolder, position: Int) {
        val items = items[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return this.items.size
    }
}