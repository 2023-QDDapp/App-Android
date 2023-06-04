package com.example.qddapp

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.databinding.FragmentCorreoVerificacionBinding

class FragmentCorreoVerificacion : DialogFragment() {

    private lateinit var binding: FragmentCorreoVerificacionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCorreoVerificacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var condicion = false

        binding.continuarRegistro.setOnClickListener {
            if (!condicion){
                binding.error.visibility = View.VISIBLE
                condicion = true
            } else {
                findNavController().navigate(R.id.fragmentRegistroNombre)
                dismiss()
            }
        }
    }
}