package com.example.agendaviewmodel.agendaUsuarioViewModel

import androidx.lifecycle.*
import com.example.agendaviewmodel.clases.UsuarioAgenda
import com.example.agendaviewmodel.repositorio.AgendaRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class UsuariosViewModel(private val repository: AgendaRepository) : ViewModel() {
    val allUsuarios: LiveData<MutableList<UsuarioAgenda>> = repository.allUsers.asLiveData()

    init {

    }

    fun insert(usuAgenda: UsuarioAgenda) = viewModelScope.launch {
        repository.insert(usuAgenda)
    }

    fun getUsuarios() : LiveData<MutableList<UsuarioAgenda>> { return allUsuarios}

}

class UsuarioViewModelFactory(private val repository: AgendaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UsuariosViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UsuariosViewModel(repository) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel class")
    }


}