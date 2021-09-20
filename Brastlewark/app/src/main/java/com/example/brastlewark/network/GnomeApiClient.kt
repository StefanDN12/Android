package com.example.brastlewark.network

import com.example.brastlewark.GnomeClass
import retrofit2.Response

import retrofit2.http.GET

interface GnomeApiClient {
    @GET("/.json")
    suspend fun getAllGnomes(): Response<List<GnomeClass>>
}