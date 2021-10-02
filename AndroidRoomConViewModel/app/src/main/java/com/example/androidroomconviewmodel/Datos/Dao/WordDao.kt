package com.example.androidroomconviewmodel.Datos.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidroomconviewmodel.Entidad.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {

    /*
    Para observar los cambios en los datos, usarás un flujo de kotlinx-coroutines. Usa un valor de muestra de tipo Flow
    en la descripción del método y Room generará todo el código necesario para actualizar Flow cuando se actualice la base de datos.

         Un flujo es una secuencia asíncrona de valores

        Flow produce valores de a uno (en lugar de todos juntos) que pueden generar valores a partir de
        operaciones asíncronas, como solicitudes de red, llamadas a bases de datos o cualquier otro código asíncrono.
        Admite corrutinas en toda su API, por lo que también puedes transformar un flujo utilizando corrutinas.
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()


    /*
    Veamos cómo hacerlo paso a paso.

    WordDao es una interfaz. Los DAO deben ser interfaces o clases abstractas.

    La anotación @Dao lo identifica como una clase de DAO para Room.

    suspend fun insert(word: Word): Declara una función de suspensión para insertar una palabra.

    La anotación @Insert es una anotación de método DAO especial para la que no necesitas proporcionar
    un SQL. (También hay anotaciones @Delete y @Update para borrar y actualizar filas, pero no las utilizarás en esta app).

    onConflict = OnConflictStrategy.IGNORE: La estrategia onConflict seleccionada ignorará una palabra
    nueva si es exactamente la misma que una que ya esté en la lista. Para obtener más información sobre las
    estrategias de conflicto disponibles, consulta la documentación.

    suspend fun deleteAll(): Declara una función suspendida para borrar todas las palabras.

    No hay ninguna anotación conveniente para borrar varias entidades, por lo que se anota con el @Query genérico.

    @Query("DELETE FROM word_table"): @Query requiere que proporciones una consulta de SQL como un parámetro de string a la
    anotación, lo que permite realizar consultas de lectura complejas y otras operaciones.

    fun getAlphabetizedWords(): List<Word>: Es el método para obtener todas las palabras y mostrar una List de Words.

    @Query("SELECT * FROM word_table ORDER BY word ASC"): Es una búsqueda que muestra una lista de palabras ordenadas de forma ascendente.
    */
}