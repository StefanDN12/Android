package com.example.doglist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class DogAdapter(val  images: List<String>, private val context : Context):RecyclerView.Adapter<DogViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false),context)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item : String = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }

}