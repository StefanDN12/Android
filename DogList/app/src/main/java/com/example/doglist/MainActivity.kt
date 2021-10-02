package com.example.doglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

import android.widget.Toast


import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doglist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter

    private var dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        //initRecyclerView()
    }


    private fun initRecyclerView(){
        adapter = DogAdapter(dogImages,this)
        binding.recyclerDogs.layoutManager= LinearLayoutManager(this)
        binding.recyclerDogs.adapter = adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<DogsResponse> = getRetrofit().create(ApiService::class.java).getDogsByBreeds("$query/images")
            val  puppies : DogsResponse? = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                   dogImages = (puppies?.imagenes as MutableList<String>)
//                    dogImages.clear()
//                    dogImages.addAll(images)
//                    adapter.notifyDataSetChanged()
                      initRecyclerView()
                }else{
                    showError()
                }
            }

        }
    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}