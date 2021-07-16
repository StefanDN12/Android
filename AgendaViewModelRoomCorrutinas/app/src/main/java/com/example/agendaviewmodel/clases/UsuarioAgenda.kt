package com.example.agendaviewmodel.clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UsuarioAgenda (
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "nombre") var nombre : String,
    @ColumnInfo(name = "apellido") var apellido : String,
    @ColumnInfo(name = "numero") var numero : Int
    )
