package com.example.qddapp.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.qddapp.Home
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.FragmentGoogle
import com.example.qddapp.UI.popUp.FragmentRegistroCorreo
import com.example.qddapp.UI.popUp.FragmentTerminosYCondiciones
import com.example.qddapp.databinding.FragmentInicioSesionBinding

class FragmentInicioSesion : Fragment() {

    private var _binding: FragmentInicioSesionBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioSesionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registroEmail.setOnClickListener {
            FragmentRegistroCorreo().show(parentFragmentManager, "showPopUp")
        }

        binding.terminosInicio.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.registroGoogle.setOnClickListener {
            FragmentGoogle().show(childFragmentManager, "TAG")
        }

        binding.logo.setOnClickListener {
            val intent = Intent(context, Home::class.java)
            startActivity(intent)
        }

        binding.iniciarSesion.setOnClickListener {
            validation(binding.emailRegistro.text.toString(), binding.contrasenaRegistro.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validation(email: String, password: String) {
        if (email.isEmpty()) {
            binding.emailRegistro.error = "Por favor, escribe aquí su email"
            return
        }
        if (password.isEmpty()) {
            binding.contrasenaRegistro.error = "Por favor, escribe aquí tu contraseña"
            return
        }
        findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentPantallaCarga)
    }
}