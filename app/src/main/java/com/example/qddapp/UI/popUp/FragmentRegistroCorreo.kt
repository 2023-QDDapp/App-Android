package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.qddapp.Modelos.RegistroBody
import com.example.qddapp.MyApp
import com.example.qddapp.databinding.FragmentRegistroCorreoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentRegistroCorreo : DialogFragment() {

    private lateinit var binding: FragmentRegistroCorreoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentRegistroCorreoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        binding.continuar.setOnClickListener {
            validation(binding.email.text.toString(), binding.contrasena.text.toString(), binding.contrasenaRepetida.text.toString())
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

        val myApp = (requireActivity().application as MyApp)
        val miRepositorio = (requireActivity().application as MyApp).repositorio
        var registro = RegistroBody( binding.email.text.toString(), binding.contrasena.text.toString())

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.registroBody(registro)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let {
                        myApp.datos.guardarUserId(it.id)
                        myApp.datos.guardarSesion(it.email, binding.contrasena.text.toString())
                        FragmentCorreoVerificacion().show(childFragmentManager, "tag")
//                        garvinguerreroalvaro@gmail.com
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

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun DialogFragment.setFullScreen() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}