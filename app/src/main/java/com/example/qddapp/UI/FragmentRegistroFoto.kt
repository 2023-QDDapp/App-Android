package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentRegistroFotoBinding

class FragmentRegistroFoto : Fragment() {

    private lateinit var binding: FragmentRegistroFotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroFotoBinding.inflate(inflater, container, false)
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

        binding.telefono.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroTelefono)
        }

        binding.siguienteFoto.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentRegistroFoto_to_fragmentRegistroDescripcion)
        }
    }
}