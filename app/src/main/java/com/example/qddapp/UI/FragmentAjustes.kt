package com.example.qddapp.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.qddapp.MainActivity
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentAjustesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAjustes : Fragment() {

    private lateinit var binding: FragmentAjustesBinding
    lateinit var miRepositorio: Repositorio

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myApp = (requireActivity().application as MyApp)
        miRepositorio = (requireActivity().application as MyApp).repositorio

        binding.cerrarSesion.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val respuesta = miRepositorio.cerrarSesion()
                withContext(Dispatchers.Main){
                    if(respuesta.isSuccessful){
                        myApp.datos.eliminarDatos()

                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finishAffinity()
                    }
                }
            }
        }

        binding.misSeguidos.setOnClickListener {
            arguments?.let {
                val id = it.getInt("id")
                val bundle = bundleOf("id_usuario" to id)
                findNavController().navigate(R.id.action_fragmentAjustes_to_fragmentSiguiendo , bundle)
            }
        }

        binding.atrasSiguiendo.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.eliminarCuenta.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val respuesta = miRepositorio.eliminarUsuario(myApp.datos.sacarUserId())

                withContext(Dispatchers.Main){
                    if(respuesta.isSuccessful){
                        myApp.datos.eliminarDatos()

                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finishAffinity()
                    }
                }
            }
        }
    }
}