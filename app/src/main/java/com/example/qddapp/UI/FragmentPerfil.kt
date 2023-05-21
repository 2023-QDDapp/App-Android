package com.example.qddapp.UI

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.qddapp.Adapters.ValoracionesAdapter
import com.example.qddapp.Modelos.Usuario
import com.example.qddapp.Modelos.Valoracion
import com.example.qddapp.R
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentPerfilBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPerfil : Fragment() {

    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameElUsuario(1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let {
                        rellenarDatos(respuesta)
                        configRecycler(respuesta.valoraciones)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun rellenarDatos(user: Usuario) {
        binding.edadPerfil.text = user.edad.toString() + " años"
        binding.nombrePerfil.text = user.nombre
        Glide.with(this).load(user.foto).into(binding.fotoPerfil)
        binding.descripcionPerfil.text = user.biografia

        for (interese in user.intereses) {
            val chip = Chip(context)
            chip.text = interese.categoria
            chip.isCheckable = false
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.transparente))
            chip.setChipStrokeColorResource(R.color.boton)
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
            binding.chipGroup.addView(chip)
        }
    }

    private fun configRecycler(listaValoraciones: List<Valoracion>) {
        val recyclerView = binding.recyclerViewPerfil
        val adapter = ValoracionesAdapter(listaValoraciones)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}