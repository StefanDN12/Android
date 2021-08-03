package com.example.agendaviewmodel.repositorio

import androidx.annotation.WorkerThread
import com.example.agendaviewmodel.bd.dao.UsuarioDaoAgenda

import com.example.agendaviewmodel.clases.UsuarioAgenda
import kotlinx.coroutines.flow.Flow


class AgendaRepository(private val usuarioDaoAgenda: UsuarioDaoAgenda) {
    val allUsers : Flow<MutableList<UsuarioAgenda>> = usuarioDaoAgenda.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(usuarioAgenda:UsuarioAgenda){
        usuarioDaoAgenda.insert(usuarioAgenda)
    }

}