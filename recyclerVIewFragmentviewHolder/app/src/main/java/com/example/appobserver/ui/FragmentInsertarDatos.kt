package com.example.appobserver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appobserver.MainActivity
import com.example.appobserver.clases.usuario
import com.example.appobserver.databinding.FragmentInsertarDatosBinding
import java.text.SimpleDateFormat
import java.util.*

class FragmentInsertarDatos : Fragment() {
    private var _binding : FragmentInsertarDatosBinding? = null
    private val binding get() = _binding!!


    private lateinit var usuario: usuario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInsertarDatosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCerrar.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commitNow()
        }

        val formatoDate : String = SimpleDateFormat("dd-MM-yyyy").format(Date())


        binding.AnadirUsu.setOnClickListener {
            usuario = usuario(binding.txtUsuario.text.toString(), binding.txtApellido.text.toString(), formatoDate)
            /*usu.nombre = binding.txtUsuario.text.toString()
            usu.apellido = binding.txtApellido.text.toString()
            usu.fechaInsert = dateFormat.toString()*/

            (activity as MainActivity).anadirUsuario(usuario)

            LimpiarCampos()
            fragmentManager?.beginTransaction()?.remove(this)?.commitNow()
        }
    }

    private fun LimpiarCampos() {
        binding.txtUsuario.setText("")
        binding.txtApellido.setText("")
    }
}