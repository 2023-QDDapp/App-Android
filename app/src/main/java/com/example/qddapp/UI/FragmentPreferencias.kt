package com.example.qddapp.UI

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.qddapp.FragmentError
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.UI.popUp.FragmentPermisos
import com.example.qddapp.databinding.FragmentPreferenciasBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPreferencias : Fragment() {

    private lateinit var binding: FragmentPreferenciasBinding
    private lateinit var chip: Chip

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPreferenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameTodasCategorias()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let { rellenarChip(respuesta) }
                } else {
                    FragmentError().show(childFragmentManager, "Tag")
                }
            }
        }

        binding.nombre.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroNombre)
        }

        binding.fechaNacimiento.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroNacimiento)
        }

        binding.telefono.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroTelefono)
        }

        binding.foto.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroFoto)
        }

        binding.descripcion.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroDescripcion)
        }

        binding.siguientePreferencias.setOnClickListener {
            Toast.makeText(requireContext(), binding.chipGroup.checkedChipIds.toString(), Toast.LENGTH_SHORT).show()
            if(binding.chipGroup.checkedChipIds.size !== 3) {
                binding.errorPreferencias.visibility = View.VISIBLE
            } else {
                val myApp = (requireActivity().application as MyApp)
                myApp.datos.guardarPreferencias(binding.chipGroup.checkedChipIds.toString())

                val permisosPopUp = FragmentPermisos()
                permisosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }
        }
    }

    fun rellenarChip(categorias: List<Categoria>) {
        for (categoria in categorias) {
            chip = Chip(context)
            chip.text = categoria.categoria
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
            chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
            chip.isCheckable = true
            chip.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(chip: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        (chip as Chip).chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.color_principal))
                        chip.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                    } else {
                        (chip as Chip).chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
                        chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
                    }
                }
            })
            binding.chipGroup.addView(chip)
        }
    }
}