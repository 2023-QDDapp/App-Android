package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.FragmentCategorias
import com.example.qddapp.databinding.FragmentCrearBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class FragmentCrear : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCrearBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCrearBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seleccionaCategoria.setOnClickListener {
            FragmentCategorias().show(childFragmentManager, "showPopUp")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_HYBRID //El tipo del mapa se vuelve hibrido
        map.isTrafficEnabled = true

        map.setOnMapClickListener {
            findNavController().navigate(R.id.action_crear_to_fragmentMapa)
        }
    }
}