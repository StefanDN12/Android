package com.example.viewmodelrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodelrecycler.Adapter.numeroAdapter
import com.example.viewmodelrecycler.databinding.ActivityMainBinding
import com.example.viewmodelrecycler.viewModel.numeroViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding


    private lateinit var adapter : numeroAdapter

    private lateinit var _numeroEANVM : numeroViewModel

    private var num : Int = 0
    private var numeroArr : MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpView()
    }

    fun setUpView(){

        adapter = numeroAdapter()
        adapter.numeroAdapter(numeroArr,this)
        binding.recyclerViewNumeros.adapter = adapter

        binding.recyclerViewNumeros.setHasFixedSize(true)
        binding.recyclerViewNumeros.layoutManager = LinearLayoutManager(this)

        _numeroEANVM = ViewModelProvider(this).get(numeroViewModel::class.java)


        _numeroEANVM.getNumeroVM().observe(this, {
            UpdateUI(it)
        })



        binding.btnAndNumero.setOnClickListener {
            numeroArr.add(num)
            _numeroEANVM.setNumeroVM(numeroArr)
            num += 1

//            adapter.notifyDataSetChanged()
        }

        binding.btnRestarNumero.setOnClickListener {
            num -= 1
            numeroArr.add(num)
            _numeroEANVM.setNumeroVM(numeroArr)
        }

    }


    private fun UpdateUI(elementos : MutableList<Int>) {
        adapter.items = elementos
        adapter.notifyDataSetChanged()

        binding.txtPrueba.text = "Tengo ${elementos.size} elementos"

        if (num > 0) {
            binding.txtPrueba.setTextColor(this.getColor(R.color.white))
        }else {
            binding.txtPrueba.setTextColor(this.getColor(R.color.black))
        }
    }
}