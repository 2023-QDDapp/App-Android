package com.example.qddapp.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qddapp.Home
import com.example.qddapp.databinding.FragmentPantallaCargaBinding

class FragmentPantallaCarga : Fragment() {

    private lateinit var binding: FragmentPantallaCargaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPantallaCargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent(context, Home::class.java)
        startActivity(intent)
        requireActivity().finishAffinity()
    }
}