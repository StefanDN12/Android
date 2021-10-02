package com.example.brastlewark.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/rrafols/mobile_test/master/data")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}