package com.example.brastlewark.ViewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brastlewark.GnomeClass
import com.example.brastlewark.databinding.GnomeCardBinding


class GnomeViewHolder(view: View, private val context : Context):RecyclerView.ViewHolder(view) {

    private val binding =GnomeCardBinding.bind(view)

    fun bind(item : GnomeClass){
        Glide.with(context).load(item.thumbnail).into(binding.thumbnail);
        binding.age.text = item.age.toString()
        binding.name.text = item.name
        binding.height.text = item.height.toString()
        binding.weight.text = item.weight.toString()
    }
}