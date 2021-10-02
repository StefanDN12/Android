package com.example.brastlewark.domain

import com.example.brastlewark.data.BrastlewarkModel
import com.example.brastlewark.data.BrastlewarkRepositori
import javax.inject.Inject


class GetAllCitizenRoom @Inject constructor(private val repositori: BrastlewarkRepositori) {

    suspend operator fun invoke()=repositori.getAllBrastlewarkRoom()
}