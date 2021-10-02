package com.example.brastlewark.data.room

import androidx.room.*
import com.example.brastlewark.data.BrastlewarkModel
import com.example.brastlewark.data.CitizenModel


@Dao
interface BrastlewarkDao {
    @Query("SELECT * FROM CitizenTable")
    suspend fun getAllCitizen(): List<CitizenModel>?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(citizenModel: CitizenModel)
}