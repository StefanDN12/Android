package com.example.agendaviewmodel

import android.app.Application
import com.example.agendaviewmodel.bd.dao.AppDatabase.AppDatabase
import com.example.agendaviewmodel.repositorio.AgendaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UsuariosApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { AgendaRepository(database.UsuarioDaoAgenda()) }
}