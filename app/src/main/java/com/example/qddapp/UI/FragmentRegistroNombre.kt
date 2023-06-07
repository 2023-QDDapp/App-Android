package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentRegistroNombreBinding

class FragmentRegistroNombre : Fragment() {

    private lateinit var binding: FragmentRegistroNombreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroNombreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.siguienteNombre.setOnClickListener {
            validation(binding.name.text.toString())
        }
    }

    private fun validation(name: String){
        if (name.isEmpty()){
            binding.name.error = "Por favor, escriba aqui su nombre"
            return
        }

        val myApp = (requireActivity().application as MyApp)

        myApp.datos.guardarNombre(binding.name.text.toString())
        findNavController().navigate(R.id.action_fragmentRegistroNombre_to_fragmentRegistroNacimiento)
    }
}