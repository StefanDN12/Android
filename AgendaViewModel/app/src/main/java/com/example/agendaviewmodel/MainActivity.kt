package com.example.agendaviewmodel


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.agendaviewmodel.Adapter.ui.AgregarUsuarios
import com.example.agendaviewmodel.Adapter.ui.MostrarUsuarios
import com.example.agendaviewmodel.agendaUsuarioViewModel.UsuarioViewModelFactory
import com.example.agendaviewmodel.agendaUsuarioViewModel.UsuariosViewModel
import com.example.agendaviewmodel.databinding.ActivityMainBinding
import com.example.agendaviewmodel.repositorio.AgendaRepository

class MainActivity : AppCompatActivity() {

    //var contador = 0

    lateinit var binding: ActivityMainBinding

    val vieM : UsuariosViewModel by viewModels {
        UsuarioViewModelFactory((application as UsuariosApp).repository)
    }
    private var repo : AgendaRepository? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    fun initView(){
        cargarRecyclerView()
        cargarAnadirDatos()

        setupView()
    }

    fun setupView() {
        vieM.getUsuarios().observe(this, {
            binding.cantidad = "${it.size}"
        })

//        binding.txtSuperior.setOnClickListener{
//            contador++
//            binding.cantidad = "${contador}"
//        }
    }

    fun cargarRecyclerView(){
        val transaction : FragmentTransaction =
            supportFragmentManager.beginTransaction().setCustomAnimations(
               R.anim.slide_in,
                R.anim.fade_out
            )
        transaction.replace(R.id.linearMostrar, MostrarUsuarios(vieM))
        transaction.commit()
    }

    fun cargarAnadirDatos(){
        binding.btnAnadir.setOnClickListener {
            val transaction : FragmentTransaction =
                supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out
                )
            transaction.replace(R.id.lineaAnadir, AgregarUsuarios(vieM))
            transaction.commit()
            mostrarOcultarBtn(false)
        }
    }

    fun mostrarOcultarBtn(valor:Boolean){
        if(valor){
            binding.btnAnadir.visibility = View.VISIBLE
            binding.rltMain.visibility = View.GONE
        }else{
            binding.btnAnadir.visibility = View.GONE
            binding.rltMain.visibility = View.VISIBLE

        }
    }
}