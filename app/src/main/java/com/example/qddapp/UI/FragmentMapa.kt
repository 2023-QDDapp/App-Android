package com.example.qddapp.UI

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FragmentMapa : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapaBinding
    private lateinit var circulo : Circle
    private lateinit var marker: Marker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMapaBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.atrasMapa.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_HYBRID //El tipo del mapa se vuelve hibrido
        val uiSettings = map.uiSettings // Para poder habilitar funciones en el mapa

        uiSettings.isZoomControlsEnabled = true // Controles de zoom

        uiSettings.isZoomGesturesEnabled = true // Gestos de zoom

        uiSettings.isRotateGesturesEnabled = true

        map.setOnMapClickListener {
            if(::circulo.isInitialized){
                circulo.remove()
                if (::marker.isInitialized){
                    marker.remove()
                    val marcador = MarkerOptions()
                        .position(it)
                        .title("Usted esta aqui")
                        .snippet("Arrastre para colocarse en otro lugar")
                        .flat(true)
                        .draggable(true) //Nos permite mover los marcadores si lo dejamos pulsado
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    marker = map.addMarker(marcador)!!
                    marker?.tag = "localizacion"
                    AñadirCirculo(marker, map)
                } else{
                    val marcador = MarkerOptions()
                        .position(it)
                        .title("Usted esta aqui")
                        .snippet("Arrastre para colocarse en otro lugar")
                        .flat(true)
                        .draggable(true) //Nos permite mover los marcadores si lo dejamos pulsado
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    marker = map.addMarker(marcador)!!
                    marker?.tag = "localizacion"
                    AñadirCirculo(marker, map)
                }
            } else {
                val marcador = MarkerOptions()
                    .position(it)
                    .title("Usted esta aqui")
                    .snippet("Arrastre para colocarse en otro lugar")
                    .flat(true)
                    .draggable(true) //Nos permite mover los marcadores si lo dejamos pulsado
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                marker = map.addMarker(marcador)!!
                marker?.tag = "localizacion"
                AñadirCirculo(marker, map)
            }
        }

        map.setOnMapLoadedCallback {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(38.0944131,-3.6309966), 18f))
        }

        map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {

            override fun onMarkerDrag(p0: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
                AñadirCirculo(marker, map)
            }

            override fun onMarkerDragStart(p0: Marker) {
                if(::circulo.isInitialized){
                    circulo.remove()
                }
            }
        })
    }

    fun AñadirCirculo(marker: Marker, map: GoogleMap) {
        val latLng1 = marker.position
        val latitud = latLng1.latitude
        val longitud = latLng1.longitude

        val circleOptions = CircleOptions()
            .center(LatLng(latitud, longitud))
            .radius(50.0)
            .strokeColor(R.color.color_principal)
            .strokeWidth(5f)
            .clickable(true)
            .fillColor(ContextCompat.getColor(requireContext(), R.color.semiTransparente))
        circulo = map.addCircle(circleOptions)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(circleOptions.center!!, 18f))
    }
}