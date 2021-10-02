package com.example.brastlewark.data.network

import com.example.brastlewark.data.BrastlewarkModel
import retrofit2.Response
import retrofit2.http.GET

interface BrastlewarkApiCliente {
    @GET("data.json")
    suspend fun getAllCitizen(): Response<BrastlewarkModel>
}