package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentRegistroTelefonoBinding

class FragmentRegistroTelefono : Fragment() {

    private lateinit var binding: FragmentRegistroTelefonoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroTelefonoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nombre.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroNombre)
        }

        binding.fechaNacimiento.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroNacimiento)
        }

        binding.siguienteTelefono.setOnClickListener {
            if (binding.inputTelefono.toString().isEmpty()) {
                binding.inputTelefono.error = "Por favor, escribe aqui tu telefono"
            } else if (binding.inputTelefono.length() == 8) {
                binding.inputTelefono.error = "El telefono no es valido"
            } else {

                Log.d("dato_telefono", binding.inputTelefono.text.toString())

                val myApp = (requireActivity().application as MyApp)
                myApp.datos.guardarTelefono(binding.inputTelefono.text.toString())

                findNavController().navigate(R.id.action_fragmentRegistroTelefono_to_fragmentRegistroFoto)
            }
        }
    }
}