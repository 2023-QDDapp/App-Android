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
import com.example.qddapp.databinding.FragmentAjustesBinding

class FragmentAjustes : Fragment() {

    private lateinit var binding: FragmentAjustesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cerrarSesion.setOnClickListener {
            val myApp = (requireActivity().application as MyApp)

            myApp.datos.eliminarDatos()

            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }

        binding.misSeguidos.setOnClickListener {
            arguments?.let {
                val id = it.getInt("id")
                val bundle = bundleOf("id_usuario" to id)
                findNavController().navigate(R.id.action_fragmentAjustes_to_fragmentSiguiendo , bundle)
            }
        }
    }
}