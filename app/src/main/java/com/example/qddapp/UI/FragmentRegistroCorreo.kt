package com.example.qddapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentRegistroCorreoBinding

class FragmentRegistroCorreo : DialogFragment() {

    private lateinit var binding: FragmentRegistroCorreoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentRegistroCorreoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continuar.setOnClickListener {
            var errores = 0
            if (binding.email.text.toString().isEmpty()) {
                binding.email.error = "Por favor, escribe aquí tu email"
                errores++
            }
            if (binding.contrasena.text.toString().isEmpty()) {
                binding.contrasena.error = "Por favor, escribe aquí tu contraseña"
                errores++
            }
            if (binding.contrasenaRepetida.text.toString().isEmpty()) {
                binding.contrasenaRepetida.error = "Por favor, escribe aquí de nuevo la contraseña"
                errores++
            }
            if (binding.contrasena.text.toString() != binding.contrasenaRepetida.text.toString()) {
                binding.contrasenaRepetida.error = "Las dos contraseñas deben ser iguales"
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

            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
            dismiss()
        }

        binding.terminosCorreo.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.cerrarCorreo.setOnClickListener {
            dismiss()
        }
    }


    private fun validation(email: String, password: String, passwordDuplicated: String) {
        if (email.isEmpty()) {
            binding.email.error = "Por favor, escribe aquí su email"
            return
        }
        if (password.isEmpty()) {
            binding.contrasena.error = "Por favor, escribe aquí tu contraseña"
            return
        }
        if (passwordDuplicated.isEmpty()) {
            binding.contrasenaRepetida.error = "Por favor, escribe aquí de nuevo la contraseña"
            return
        }
        if (password != passwordDuplicated){
            binding.contrasena.error = "Las dos contraseñas deben ser iguales"
            return
        }
        findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
    }
}