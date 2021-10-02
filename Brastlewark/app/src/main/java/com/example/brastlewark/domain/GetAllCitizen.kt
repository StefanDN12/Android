package com.example.brastlewark.domain

import com.example.brastlewark.data.BrastlewarkModel
import com.example.brastlewark.data.BrastlewarkRepositori
import retrofit2.Response
import javax.inject.Inject

class GetAllCitizen @Inject constructor(private val repositori: BrastlewarkRepositori) {

    suspend operator fun invoke(): BrastlewarkModel? =repositori.getAllBrastlewark()

}