package com.example.agendaviewmodel.repositorio

import androidx.annotation.WorkerThread
import com.example.agendaviewmodel.bd.dao.UsuarioDaoAgenda
import com.example.agendaviewmodel.clases.UsuarioAgenda
import kotlinx.coroutines.flow.Flow


class AgendaRepository( private val usuarioDaoAgenda: UsuarioDaoAgenda) {


    val allUsers : Flow<List<UsuarioAgenda>> = UsuarioDaoAgenda.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun inser(usuarioDaoAgenda:UsuarioAgenda){
        UsuarioDaoAgenda.insert(usuarioDaoAgenda)
    }

}