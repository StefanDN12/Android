package com.example.doglist

<<<<<<< HEAD
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doglist.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View, private val context: Context):RecyclerView.ViewHolder(view) {
=======
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View):RecyclerView.ViewHolder(view) {
>>>>>>> main

    private val binding = ItemDogBinding.bind(view)

    fun bind (image: String){
<<<<<<< HEAD
        //Picasso.get().load(image).into(binding.ivDog)
        Glide.with(context).load(image).into(binding.ivDog);
=======
        Picasso.get().load(image).into(binding.ivDog)
>>>>>>> main
    }
}