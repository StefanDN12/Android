package com.example.androidroomconviewmodel.Repositorio

import androidx.annotation.WorkerThread
import com.example.androidroomconviewmodel.Datos.Dao.WordDao
import com.example.androidroomconviewmodel.Entidad.Word
import kotlinx.coroutines.flow.Flow


/*
Una clase de repositorio abstrae el acceso a múltiples fuentes de datos.
El repositorio no forma parte de las bibliotecas de componentes de la arquitectura,
pero es una práctica recomendada para la separación del código y su arquitectura.
Una clase de repositorio proporciona una API limpia para el acceso de datos al resto de la aplicación.
 */

/*
¿Por qué usar un repositorio?
Un repositorio administra las consultas y te permite usar varios backends.
En el ejemplo más común, el repositorio implementa la lógica para decidir si debe recuperar
datos de una red o usar resultados almacenados en caché de una base de datos local
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepositorio(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    /*
    Las ideas principales son las siguientes:

        El DAO se pasa al constructor del repositorio, en lugar de la base de datos completa.
        Esto se debe a que solo necesita acceso al DAO, ya que contiene todos los métodos de lectura y
        escritura de la base de datos. No es necesario exponer toda la base de datos en el repositorio.

        La lista de palabras es una propiedad pública. Se inicializa al obtener la lista de palabras Flow de Room.
        Esto es posible debido a la forma en la que definimos el método getAlphabetizedWords para que muestre Flow en el paso
        "Observar cambios en la base de datos". Room ejecuta todas las consultas en un subproceso separado.

        El modificador suspend le indica al compilador que debe llamarse desde una corrutina o desde otra función de suspensión.

        Room ejecuta las consultas suspendidas fuera del subproceso principal.
     */
}