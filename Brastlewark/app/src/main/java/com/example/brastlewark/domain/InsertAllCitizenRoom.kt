package com.example.brastlewark.domain


import com.example.brastlewark.data.BrastlewarkRepositori
import com.example.brastlewark.data.CitizenModel
import javax.inject.Inject


class InsertAllCitizenRoom @Inject constructor(private val repositori: BrastlewarkRepositori) {

   suspend fun insertAllCitizenRoom(citizenModel: CitizenModel){
      repositori.insertBrastlewarkRoom(citizenModel)
   }
}