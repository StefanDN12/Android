package com.example.brastlewark.network

import com.example.brastlewark.GnomeClass
import com.example.brastlewark.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GnomeService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getGnomes():List<GnomeClass>{
        return withContext(Dispatchers.IO){
            val response: Response<List<GnomeClass>> = retrofit.create(GnomeApiClient::class.java).getAllGnomes()
            response.body()?: emptyList()
        }

    }

}