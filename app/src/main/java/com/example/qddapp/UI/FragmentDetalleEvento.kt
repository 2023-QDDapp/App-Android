package com.example.qddapp.UI

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.MarginLayoutParamsCompat.setMarginStart
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.FragmentAsistentes
import com.example.qddapp.UI.popUp.FragmentSolicitudEnviada
import com.example.qddapp.databinding.FragmentDetalleEventoBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentDetalleEvento : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetalleEventoBinding
    private lateinit var marker: Marker
    var latitud: Double = 0.0
    var longitud: Double = 0.0
    var titulo: String = ""
    var id: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetalleEventoBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        arguments?.let {
            id = it.getInt("id")
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameElEvento(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            rellenarDatos(respuesta)
                            latitud = respuesta.latitud
                            longitud = respuesta.longitud
                            titulo = respuesta.titulo
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

        binding.asistentes.setOnClickListener {
            FragmentAsistentes().show(childFragmentManager, "TAG")
        }

        binding.atrasDetalleEvento.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.solicitarUnirse.setOnClickListener {
            FragmentSolicitudEnviada().show(childFragmentManager, "TAG")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_HYBRID //El tipo del mapa se vuelve hibrido
        map.isTrafficEnabled = true

        map.setOnMapLoadedCallback {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitud, longitud), 15f))

            val marcador = MarkerOptions()
                .position(LatLng(latitud, longitud))
                .title(titulo)
                .flat(true)
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            marker = map.addMarker(marcador)!!
        }
    }

    fun rellenarDatos(evento : Evento) {
        Glide.with(this).load(evento.imagenEvento).into(binding.fotoDetalleEvento)
        binding.nombreDetalleEvento.text = evento.descripcion
        Glide.with(this).load(evento.fotoOrganizador).into(binding.fotoPerfilOrganizadorDetalleEvento)
        binding.nombreOrganizadorDetalleEvento.text = evento.organizador
        binding.edadDetalleEvento.text = evento.edad.toString() + " años"
        binding.descripcionDetalleEvento.text = evento.descripcion
        binding.localizacionDetalleEvento.text = evento.location
        agregarAsistente(evento.asistentes)
    }

    var primero = true
    var margen = 0
    var num = 100
    var num2 = 17
    var contador = 1
    @SuppressLint("ResourceType")
    fun agregarAsistente(asistentes: List<Asistente>?) {
        if (asistentes!!.count() > 3){
            for (asistente in asistentes) {
                contador++
                when (contador) {
                    1, 2, 3 -> {
                        val imagen = CircleImageView(context)
                        var params = LayoutParams(100, LayoutParams.MATCH_PARENT)
                        imagen.layoutParams = params
                        imagen.scaleType = ImageView.ScaleType.CENTER_CROP
                        if(primero){
                            binding.asistentes.addView(imagen)
                            Glide.with(this).load(asistente.foto).into(imagen)
                            primero = false
                        } else {
                            margen = num - num2
                            setMarginStart(params, margen)
                            num += 100
                            num2 += 17
                            binding.asistentes.addView(imagen)
                            Glide.with(this).load(asistente.foto).into(imagen)
                        }
                    }
                    4 -> {
                        val imagen = CircleImageView(context)
                        var params = LayoutParams(100, LayoutParams.MATCH_PARENT)
                        imagen.layoutParams = params
                        imagen.scaleType = ImageView.ScaleType.CENTER_CROP
                        imagen.setBackgroundColor(R.color.color_secundario)
                        Glide.with(this).load(R.drawable.baseline_add_24).into(imagen)
                        primero = false
                        margen = num - num2
                        setMarginStart(params, margen)
                        binding.asistentes.addView(imagen)
                    }
                }
            }
        } else {
            for (asistente in asistentes) {
                val imagen = CircleImageView(context)
                var params = LayoutParams(100, LayoutParams.MATCH_PARENT)
                imagen.layoutParams = params
                imagen.scaleType = ImageView.ScaleType.CENTER_CROP
                if(primero){
                    binding.asistentes.addView(imagen)
                    Glide.with(this).load(asistente.foto).into(imagen)
                    primero = false
                } else {
                    margen = num - num2
                    setMarginStart(params, margen)
                    num += 100
                    num2 += 17
                    binding.asistentes.addView(imagen)
                    Glide.with(this).load(asistente.foto).into(imagen)
                }
            }
        }
    }
}
