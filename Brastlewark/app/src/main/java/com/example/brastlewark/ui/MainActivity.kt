package com.example.brastlewark.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import com.example.brastlewark.R
import com.example.brastlewark.data.CitizenModel
import com.example.brastlewark.databinding.ActivityMainBinding
import com.example.brastlewark.ui.adapters.BrastlewarkAdapter
import com.example.brastlewark.ui.fragments.FragmentDetail
import com.example.brastlewark.ui.viewModel.BrastlewarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val brastlewarkViewModel: BrastlewarkViewModel by viewModels()
    private lateinit var adapter: BrastlewarkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        brastlewarkViewModel.onCreate()

         brastlewarkViewModel.citizen.observe(this, {
             adapter = BrastlewarkAdapter(it, this, listener)
             binding.recyclerGnome.adapter = adapter
             binding.recyclerGnome.setHasFixedSize(true)

             it.forEach{ citizen ->
                 brastlewarkViewModel.insertCitizen(citizen)
             }
        })
        brastlewarkViewModel.isLoading.observe(this, {
            if(it){
                binding.progress.visibility = View.VISIBLE
            }else{
                binding.progress.visibility = View.GONE
            }
        })

        binding.reserchCitizen.setOnQueryTextListener(object:SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }
    private val listener :(citizen : CitizenModel) -> Unit = { citizen ->
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FragmentDetail(citizen), "DETAIL_FRAGMENT").commit()
    }
}