package com.example.brastlewark.data.room

import android.content.Context
import androidx.room.*
import com.example.brastlewark.data.CitizenModel
import javax.inject.Inject


@Database(entities = arrayOf(CitizenModel::class),version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BrastlewarkDatabase: RoomDatabase() {
    abstract fun getBrastlewarkDao():BrastlewarkDao
}