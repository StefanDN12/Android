package com.example.doglist

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doglist.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View, private val context: Context):RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind (image: String){
        //Picasso.get().load(image).into(binding.ivDog)
        Glide.with(context).load(image).into(binding.ivDog);
    }
}