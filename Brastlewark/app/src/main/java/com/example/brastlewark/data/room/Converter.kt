package com.example.brastlewark.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converter {
    @TypeConverter
    fun listToJson(list: List<String>) = Gson().toJson(list)
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}