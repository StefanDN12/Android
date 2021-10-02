package com.example.brastlewark.clases

import com.example.brastlewark.GnomeClass
import com.google.gson.annotations.SerializedName

data class populationClass (
                @SerializedName("Brastlewark")var population : List<GnomeClass>
        )