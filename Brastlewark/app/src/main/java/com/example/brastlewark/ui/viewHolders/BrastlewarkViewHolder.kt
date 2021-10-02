package com.example.brastlewark.ui.viewHolders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brastlewark.core.Utils
import com.example.brastlewark.data.BrastlewarkModel
import com.example.brastlewark.data.CitizenModel
import com.example.brastlewark.databinding.GnomeCardBinding
import com.example.brastlewark.ui.viewModel.BrastlewarkViewModel

class BrastlewarkViewHolder(view: View,private val context: Context): RecyclerView.ViewHolder(view)
{
    private val binding = GnomeCardBinding.bind(view)

    fun bind(citizenModel: CitizenModel){
        Glide.with(context).load(Utils().generateGlideUrl(citizenModel.thumbnail)).into(binding.thumbnail);

        binding.name.text = "Name: "+citizenModel.name
        binding.age.text = "Age: "+citizenModel.age.toString()

        binding.weight.text = "Weight: "+ citizenModel.weight.toString()
        binding.height.text = "Height: " + citizenModel.height.toString()



    }
}