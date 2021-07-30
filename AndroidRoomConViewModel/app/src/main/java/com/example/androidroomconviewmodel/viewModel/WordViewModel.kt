package com.example.androidroomconviewmodel.viewModel

import androidx.lifecycle.*
import com.example.androidroomconviewmodel.Entidad.Word
import com.example.androidroomconviewmodel.Repositorio.WordRepositorio
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepositorio) : ViewModel() {


    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepositorio) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

/*
Desglosemos este código. Esto es lo que hiciste:

Creaste una clase llamada WordViewModel que obtiene el WordRepository como parámetro y extiende ViewModel.
El repositorio es la única dependencia que ViewModel necesita. Si se hubieran necesitado otras clases, también se
habrían aprobado en el constructor.

Agregaste una variable de miembro LiveData pública para almacenar en caché la lista de palabras.

Inicializaste el LiveData con el flujo allWords desde el repositorio y, luego, llamaste a asLiveData(). para convertir el flujo en LiveData.

Creaste un método insert() de wrapper que llama al método insert() del repositorio. De esta manera, la implementación de
insert() se encapsula desde la IU, lanzamos una corrutina nueva y llamamos a la inserción del repositorio, que es una función
suspendida. Como se mencionó antes, ViewModels tiene un alcance de corrutinas basado en los ciclos de vida llamado viewModelScope, que usarás aquí.

Creaste el ViewModel mediante la implementación de un ViewModelProvider.Factory que obtiene las dependencias necesarias para
crear WordViewModel como parámetro: el WordRepository.
 */