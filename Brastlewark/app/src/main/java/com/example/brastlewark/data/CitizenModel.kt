package com.example.brastlewark.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


@Entity(tableName = "CitizenTable")
data class CitizenModel(
    @PrimaryKey
    @SerializedName("id")var id : Int = 0,
    @SerializedName("name")var name : String = "",
    @SerializedName("thumbnail")var thumbnail : String = "",
    @SerializedName("age")var age : Int = 0,
    @SerializedName("weight")var weight : Double = 0.0,
    @SerializedName("height")var height : Double = 0.0,
    @SerializedName("hair_color")var hairColor : String = "",
    @SerializedName("professions")var professions : List<String>,
    @SerializedName("friends")var friends : List<String>
)