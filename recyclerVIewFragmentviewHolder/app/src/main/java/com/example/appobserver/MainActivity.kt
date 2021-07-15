package com.example.appobserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.appobserver.clases.usuario
import com.example.appobserver.databinding.ActivityMainBinding
import com.example.appobserver.ui.FragmentInsertarDatos
import com.example.appobserver.ui.FragmentRecyclerView
import com.example.appobserver.viewModels.viewModelPrueba
import com.example.appobserver.ws.bd
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

        lateinit var binding : ActivityMainBinding
        private lateinit var db: bd

        private lateinit var _UsuariosEANVM : viewModelPrueba

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = bd()

        setUpView()
    }


    fun setUpView(){
        _UsuariosEANVM = ViewModelProvider(this).get(viewModelPrueba::class.java)

        _UsuariosEANVM.getUsuarioVM().observe(this, {

        })


        anadirNombres()
    }

    fun cargarRecyclerView(){
        val transaction : FragmentTransaction =
            supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out
            )

        transaction.replace(R.id.frmRecycler,FragmentRecyclerView(db))

        transaction.commit()
    }


    fun anadirNombres(){
        binding.btnAnadirUsuario.setOnClickListener {
            val transaction : FragmentTransaction =
                supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out
                )

            transaction.replace(R.id.frmCambio,FragmentInsertarDatos())

            transaction.commit()
        }
    }

    fun anadirUsuario(usuario: usuario) {
        db.setUsuarios(usuario)
        _UsuariosEANVM.setUsuariosVM(db.getUsuario())
        cargarRecyclerView()
    }
}