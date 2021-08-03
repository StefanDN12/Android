package com.example.agendaviewmodel.Adapter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.agendaviewmodel.MainActivity
import com.example.agendaviewmodel.agendaUsuarioViewModel.UsuariosViewModel
import com.example.agendaviewmodel.clases.UsuarioAgenda
import com.example.agendaviewmodel.databinding.FragmentAgregarUsuariosBinding
import com.example.agendaviewmodel.utils.Global


class AgregarUsuarios(vm : UsuariosViewModel) : Fragment() {

    private var _binding : FragmentAgregarUsuariosBinding? = null
    private val binding get() = _binding!!

    private var vm : UsuariosViewModel = vm

    private var nombre : String = ""
    private var apellido : String = ""
    private var numero : Int = 0

    private lateinit var vieM : UsuariosViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgregarUsuariosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviarDT.setOnClickListener {
            (activity as MainActivity).mostrarOcultarBtn(true)
            fragmentManager?.beginTransaction()?.remove(this)?.commitNow()
            nombre = binding.editNombre.text.toString()
            apellido = binding.editApellido.text.toString()
            numero = binding.editTelefono.text.toString().toInt()



            val usuarioAgenda = UsuarioAgenda()
//            Global.global++
            usuarioAgenda.id = Global.global
            usuarioAgenda.nombre ="${nombre}"
            usuarioAgenda.apellido = "${apellido}"
            usuarioAgenda.numero = "${numero}".toInt()
            vm.insert(usuarioAgenda)


        }
    }
}