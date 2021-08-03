package com.example.agendaviewmodel.clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UsuarioAgenda {
    @PrimaryKey(autoGenerate = true) var id : Int = 0
    @ColumnInfo(name = "nombre") var nombre : String = ""
    @ColumnInfo(name = "apellido") var apellido : String = ""
    @ColumnInfo(name = "numero") var numero : Int = 0
}
