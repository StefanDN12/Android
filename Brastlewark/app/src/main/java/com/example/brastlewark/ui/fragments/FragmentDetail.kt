package com.example.brastlewark.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.brastlewark.BrastlewarkApp
import com.example.brastlewark.R
import com.example.brastlewark.core.Utils
import com.example.brastlewark.data.CitizenModel
import com.example.brastlewark.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext


@AndroidEntryPoint
class FragmentDetail(val citizen : CitizenModel) : Fragment() {
    private lateinit var _binding : FragmentDetailBinding
    private val binding get()= _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }


        context?.let {
            Glide.with(it).load(Utils().generateGlideUrl(citizen.thumbnail)).into(binding.imgCitizen)
        }

        binding.txtCitizenName.text = citizen.name
        binding.txtCitizenAge.text = citizen.age.toString()
        binding.txtCitizenProfession.text = citizen.professions.joinToString(", ","","",-1)
        binding.txtCitizenFriends.text = citizen.friends.joinToString(", ","","",-1)
    }

}