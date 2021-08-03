package com.example.agendaviewmodel.Adapter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendaviewmodel.Adapter.UsuariosAdapter
import com.example.agendaviewmodel.MainActivity
import com.example.agendaviewmodel.agendaUsuarioViewModel.UsuariosViewModel
import com.example.agendaviewmodel.databinding.FragmentMostrarUsuariosBinding
import com.example.agendaviewmodel.utils.Global


class MostrarUsuarios(vm : UsuariosViewModel) : Fragment() {
    private var _binding : FragmentMostrarUsuariosBinding? = null
    private val binding get() = _binding!!
    private var vm : UsuariosViewModel = vm

    var adapter = UsuariosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMostrarUsuariosBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView(){
        binding.AgendaUsuarios.setHasFixedSize(true)
        binding.AgendaUsuarios.layoutManager = LinearLayoutManager(context)
        binding.AgendaUsuarios.adapter = adapter

        vm.getUsuarios().observe(viewLifecycleOwner){usuarios ->
            usuarios.let {
                adapter.Usuario(it)
                adapter.notifyDataSetChanged()
                Global.global = usuarios.size + 1
            }
        }
    }
}