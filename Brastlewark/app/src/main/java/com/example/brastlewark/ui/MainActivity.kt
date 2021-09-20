package com.example.brastlewark.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brastlewark.R
import com.example.brastlewark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}