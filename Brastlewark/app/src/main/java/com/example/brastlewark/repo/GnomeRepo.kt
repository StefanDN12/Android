package com.example.brastlewark.repo

import com.example.brastlewark.GnomeClass
import com.example.brastlewark.network.GnomeService

class GnomeRepo {
    private val api = GnomeService()
    suspend fun getAllGnomes():List<GnomeClass>{
        val response = api.getGnomes()
        return response
    }
}