package com.example.brastlewark.data

import com.example.brastlewark.data.network.BrastlewarkApiCliente
import com.example.brastlewark.data.network.BrastlwarkService
import com.example.brastlewark.data.room.BrastlewarkDao
import retrofit2.Response
import javax.inject.Inject

class BrastlewarkRepositori @Inject constructor(private val api:BrastlwarkService, private val brastlewarkProvide: BrastlewarkProvide, private val brastlewarkDao: BrastlewarkDao) {
        suspend fun getAllBrastlewark() : BrastlewarkModel{
                val response: BrastlewarkModel = api.getBrastlewark()
                brastlewarkProvide.Brastlewark = response
                return response
        }

        suspend fun getAllBrastlewarkRoom() :List<CitizenModel>{
                val response: List<CitizenModel> = brastlewarkDao.getAllCitizen()!!
                return response
        }

        suspend fun insertBrastlewarkRoom(citizenModel: CitizenModel){
                brastlewarkDao.insert(citizenModel)
        }
}