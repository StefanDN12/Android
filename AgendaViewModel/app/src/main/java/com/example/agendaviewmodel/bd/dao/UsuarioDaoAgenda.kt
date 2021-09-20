package com.example.agendaviewmodel.bd.dao

import androidx.room.*
import com.example.agendaviewmodel.clases.UsuarioAgenda
import kotlinx.coroutines.flow.Flow


@Dao
interface UsuarioDaoAgenda {

    @Query("SELECT * FROM UsuarioAgenda")
    fun getAll(): Flow<MutableList<UsuarioAgenda>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioAgenda: UsuarioAgenda)

    @Query("DELETE FROM UsuarioAgenda")
    suspend fun deleteAll()
}