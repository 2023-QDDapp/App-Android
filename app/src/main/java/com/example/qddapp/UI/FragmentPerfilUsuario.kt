package com.example.qddapp.UI

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.qddapp.Adapters.ValoracionesAdapter
import com.example.qddapp.FragmentError
import com.example.qddapp.Modelos.Usuario
import com.example.qddapp.Modelos.Valoracion
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentPerfilUsuarioBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPerfilUsuario : Fragment() {

    private lateinit var binding: FragmentPerfilUsuarioBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPerfilUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        arguments?.let {
            val id = it.getInt("id_organizador")
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameElUsuario(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            rellenarDatos(respuesta)
                            configRecycler(respuesta.valoraciones)
                        }
                    } else {
                        FragmentError().show(childFragmentManager, "Tag")
                    }
                }
            }
        }

        arguments?.let {
            val id = it.getInt("id_organizador")
            binding.seguirUsuario.setOnClickListener {
                if (binding.seguirUsuario.text == "Dejar de seguir") {
                    CoroutineScope(Dispatchers.IO).launch {
                        val response = miRepositorio.dejarSeguirUsuario(id)
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful && response.code() == 200) {
                                val respuesta = response.body()
                                respuesta?.let {
                                    binding.seguirUsuario.setBackgroundColor(R.color.boton)
                                    binding.seguirUsuario.text = "Seguir"
                                }
                            } else {
                                FragmentError().show(childFragmentManager, "Tag")
                            }
                        }
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val response = miRepositorio.seguirUsuario(id)
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful && response.code() == 200) {
                                val respuesta = response.body()
                                respuesta?.let {
                                    binding.seguirUsuario.setBackgroundColor(R.color.color_principal)
                                    binding.seguirUsuario.text = "Dejar de seguir"
                                }
                            } else {
                                FragmentError().show(childFragmentManager, "Tag")
                            }
                        }
                    }
                }
            }
        }

        arguments?.let {
            val id = it.getInt("id_organizador")
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.teSigo(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            if (respuesta.mensaje == "Dejar de seguir") {
                                binding.seguirUsuario.setBackgroundColor(R.color.boton)
                                binding.seguirUsuario.text = respuesta.mensaje
                            } else {
                                binding.seguirUsuario.setBackgroundColor(R.color.color_principal)
                                binding.seguirUsuario.text = respuesta.mensaje
                            }
                        }
                    } else {
                        FragmentError().show(childFragmentManager, "Tag")
                    }
                }
            }
        }

        binding.atrasPerfilUsuario.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun rellenarDatos(user: Usuario) {
        binding.edadPerfilUsuario.text = user.edad.toString() + " años"
        binding.nombrePerfilUsuario.text = user.nombre
        Glide.with(this).load(user.foto).into(binding.fotoPerfilUsuario)
        binding.descripcionPerfilUsuario.text = user.biografia

        for (interese in user.intereses) {
            val chip = Chip(context)
            chip.text = interese.categoria
            chip.isCheckable = false
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!,
                R.color.transparente
            ))
            chip.setChipStrokeColorResource(R.color.boton)
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
            binding.chipGroupUsuario.addView(chip)
        }
    }

    private fun configRecycler(listaValoraciones: List<Valoracion>) {
        val recyclerView = binding.recyclerViewPerfilUsuario
        val adapter = ValoracionesAdapter(listaValoraciones)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}