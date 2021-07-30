package com.example.androidroomconviewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidroomconviewmodel.Adapter.WordListAdapter
import com.example.androidroomconviewmodel.Entidad.Word
import com.example.androidroomconviewmodel.app.WordsApplication
import com.example.androidroomconviewmodel.viewModel.WordViewModel
import com.example.androidroomconviewmodel.viewModel.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }


        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(reply)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

/*
Los componentes de la aplicación son los siguientes:

MainActivity: Muestra palabras en una lista mediante un RecyclerView y WordListAdapter.
En MainActivity, hay un Observer que observa las palabras de la base de datos y recibe una notificación cuando se modifican.

NewWordActivity: agrega una palabra nueva a la lista.

WordViewModel: Proporciona métodos para acceder a la capa de datos, y muestra LiveData para que MainActivity pueda
configurar la relación del observador.*

LiveData<List<Word>>: Permite que las actualizaciones sean automáticas para los componentes de la IU.
Puedes llamar a flow.toLiveData() y convertir Flow en LiveData.

Repository: administra una o más fuentes de datos. El Repository muestra los métodos para que ViewModel
interactúe con el proveedor de datos subyacente. En esta aplicación, ese backend es una base de datos de Room.

Room: es un wrapper e implementa una base de datos SQLite. Room hace por ti mucho del trabajo que solías hacer.

DAO: Asigna llamadas de método a consultas de la base de datos para que, cuando el repositorio llame a
un método como getAlphabetizedWords(), Room pueda ejecutar SELECT * FROM word_table ORDER BY word ASC**.**

DAO puede mostrar consultas suspend para solicitudes únicas y consultas Flow, cuando quieres recibir
notificaciones de los cambios en la base de datos.

Word: es la clase de entidad que contiene una sola palabra.

Views y Activities (y Fragments) solo interactúan con los datos mediante ViewModel. Por lo tanto,
es irrelevante de dónde provengan los datos.

Flujo de datos para las actualizaciones automáticas de la IU (IU reactiva)

La actualización automática es posible porque estás usando LiveData. En el MainActivity,
hay un Observer que observa las palabras LiveData de la base de datos y recibe una notificación cuando
cambian. Cuando hay un cambio, el método onChange() del observador se ejecuta y se actualiza mWords en el WordListAdapter.

Los datos pueden observarse porque son LiveData y lo que se observa es la LiveData<List<Word>>
que muestra la propiedad WordViewModel allWords.

El WordViewModel oculta toda la información del backend de la capa de IU. Proporciona
métodos para acceder a la capa de datos y muestra LiveData para que MainActivity pueda configurar la relación del
observador. Views y Activities (y Fragments) solo interactúan con los datos mediante ViewModel. Por lo tanto, es
irrelevante de dónde provengan los datos.


En este caso, los datos provienen de un Repository. El ViewModel no necesita saber con qué
interactúa el repositorio. Solo necesita saber cómo interactuar con el Repository, que se realiza con los métodos que muestra el Repository.

El repositorio administra una o más fuentes de datos. En la app WordListSample, ese backend es una base de datos de Room.
Room es un wrapper que implementa una base de datos SQLite. Room hace por ti mucho del trabajo que solías hacer.
Por ejemplo, Room hace todo lo que solías hacer con una clase SQLiteOpenHelper.

El DAO asigna llamadas de método a las consultas de la base de datos para que, cuando el repositorio llame a un
método como getAllWords(), Room pueda ejecutar SELECT * FROM word_table ORDER BY word ASC.

Como el resultado que muestra la consulta es LiveData observado, cada vez que se modifican los datos de Room,
se ejecuta el método onChanged() de la interfaz Observer y se actualiza la IU.
 */