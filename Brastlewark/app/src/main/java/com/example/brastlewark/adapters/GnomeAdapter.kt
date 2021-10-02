package com.example.brastlewark.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brastlewark.GnomeClass
import com.example.brastlewark.R
import com.example.brastlewark.ViewHolder.GnomeViewHolder

class GnomeAdapter(val gnomes:List<GnomeClass>, private val context: Context): RecyclerView.Adapter<GnomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GnomeViewHolder(layoutInflater.inflate(R.layout.gnome_card,parent, false),context)
    }

    override fun onBindViewHolder(holder: GnomeViewHolder, position: Int) {
        val item = gnomes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return gnomes.size
    }
}