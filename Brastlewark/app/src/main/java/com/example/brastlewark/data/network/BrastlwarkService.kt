package com.example.brastlewark.data.network

import com.example.brastlewark.data.BrastlewarkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class BrastlwarkService @Inject constructor(private val api: BrastlewarkApiCliente) {


    suspend fun getBrastlewark(): BrastlewarkModel{
        return withContext(Dispatchers.IO){
            val response = api.getAllCitizen()
            response.body()?: BrastlewarkModel(arrayListOf())
        }
    }
}