package com.example.agendaviewmodel.bd.dao

import androidx.room.*
import com.example.agendaviewmodel.clases.UsuarioAgenda
import kotlinx.coroutines.flow.Flow


@Dao
interface UsuarioDaoAgenda {
    @Query("SELECT * FROM UsuarioAgenda")
    fun getAll(): Flow<List<UsuarioAgenda>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioAgenda: UsuarioAgenda)

    @Delete
    suspend fun deleteAll()
}