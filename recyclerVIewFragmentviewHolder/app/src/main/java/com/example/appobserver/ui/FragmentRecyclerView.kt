package com.example.appobserver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appobserver.R
import com.example.appobserver.adapters.UsuarioAdapter
import com.example.appobserver.clases.usuario
import com.example.appobserver.databinding.FragmentRecyclerViewBinding
import com.example.appobserver.viewModels.viewModelPrueba
import com.example.appobserver.ws.bd


class FragmentRecyclerView(val db : bd) : Fragment(R.layout.fragment_recycler_view) {

    private var _binding : FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : UsuarioAdapter
    var usuarios : MutableList<usuario> = ArrayList()

    private lateinit var _UsuariosEANVM : viewModelPrueba

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usuarios = db.getUsuario()

        _UsuariosEANVM = ViewModelProvider(this).get(viewModelPrueba::class.java)



        adapter = UsuarioAdapter()

        binding.recyclesViewUsuarios.setHasFixedSize(true)
        binding.recyclesViewUsuarios.layoutManager = LinearLayoutManager(context)

        _UsuariosEANVM.getUsuarioVM().observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.recyclesViewUsuarios.adapter = adapter
    }
}