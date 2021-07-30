package com.example.androidroomconviewmodel.Datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidroomconviewmodel.Datos.Dao.WordDao
import com.example.androidroomconviewmodel.Entidad.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    /*
    ¿Qué es una base de datos Room**?**

        Room es una capa de base de datos sobre una base de datos SQLite.

        Room se ocupa de las tareas rutinarias de las que solías encargarte con SQLiteOpenHelper.

        Room usa el DAO para enviar consultas a su base de datos.

        De manera predeterminada, para evitar un rendimiento deficiente en
        la IU, Room no permite enviar consultas en el subproceso principal.
        Cuando las consultas de Room muestran Flow, las consultas se ejecutan
        automáticamente de manera asíncrona en un subproceso, en segundo plano.

        Room proporciona comprobaciones de tiempo de compilación de las sentencias de SQLite.
     */

    companion object {
        @Volatile
        private var INSTANCE : WordRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    /*
    Veamos el código paso a paso:

        La clase de la base de datos de Room debe ser abstract y extender RoomDatabase..

        Puedes anotar la clase para que sea una base de datos Room con @Database y
        usar los parámetros de anotación para declarar las entidades que pertenecen a la
        base de datos y establecer el número de versión. Cada entidad corresponde a una
        tabla que se creará en la base de datos. Las migraciones de bases de datos están fuera del
        alcance de este codelab, por lo que se configuró exportSchema como falso para evitar una advertencia
        de compilación. En una app real, te recomendamos establecer un directorio para que Room exporte el
        esquema a fin de que puedas comprobar el esquema actual en tu sistema de control de versión.
        La base de datos expone el DAO con un método "get" abstracto para cada @Dao.

        Definimos el singleton WordRoomDatabase, para evitar que se abran varias instancias de la base de datos al mismo tiempo.

        getDatabase muestra el singleton. Se creará la base de datos la primera vez que se acceda
        a ella, usando el compilador de bases de datos de Room para crear un objeto RoomDatabase en el
        contexto de la aplicación a partir de la clase WordRoomDatabase y se le asignará el nombre "word_database".
     */


    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase){
            super.onCreate(db)
            INSTANCE?.let {database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }
            }
        }
        suspend fun populateDatabase(wordDao: WordDao){
            //Delete all content here
            wordDao.deleteAll()

            //Add sample words
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }
    }
}

