package com.example.agendaviewmodel.agendaUsuarioViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.agendaviewmodel.bd.dao.AppDatabase.appDatabase
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {

    init {
        viewModelScope.launch{
            // Coroutine that will be canceled when the ViewModel is cleared.
            val db = Room.databaseBuilder(
                coroutineContext,
                appDatabase::class.java, "AgendaDB"
            ).enableMultiInstanceInvalidation().build()

        }
    }
}