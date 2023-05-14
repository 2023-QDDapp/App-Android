package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentInicioSesionBinding

class FragmentInicioSesion : Fragment() {

    private lateinit var binding: FragmentInicioSesionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentInicioSesionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registroEmail.setOnClickListener {
            val correoePopUp = FragmentRegistroCorreo()
            correoePopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        binding.terminosInicio.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.registroGoogle.setOnClickListener{
            val googlePopUp = FragmentGoogle()
            googlePopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        binding.iniciarSesion.setOnClickListener {
            var errores = 0
            if (binding.emailRegistro.text.toString().isEmpty()){
                binding.emailRegistro.error = "Por favor, escribe aquí su email"
                errores++
            }
            if (binding.contrasenaRegistro.text.toString().isEmpty()){
                binding.contrasenaRegistro.error = "Por favor, escribe aquí su contraseña"
                return@setOnClickListener
            }
            if (binding.contrasenaRegistro.text.toString() != "password"){
                binding.contrasenaRegistro.error = "Contraseña incorrecta"
                errores++
            }
            if (errores > 0) {
                return@setOnClickListener
            }

            /* He pensado hacerlo tambien así

                if (errores == 0){
                    findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentPantallaCarga)
                }
            */

            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentPantallaCarga)
        }
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
        if (password != "password"){
            binding.contrasenaRegistro.error = "Contraseña incorrecta"
            return
        }
        findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentPantallaCarga)
    }
}