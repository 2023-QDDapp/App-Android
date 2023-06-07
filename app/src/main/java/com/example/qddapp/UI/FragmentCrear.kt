package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qddapp.MyApp
import com.example.qddapp.MyViewModel
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.DatePickerFragment
import com.example.qddapp.UI.popUp.FragmentCategorias
import com.example.qddapp.UI.popUp.FragmentFotoEvento
import com.example.qddapp.UI.popUp.TimePickerFragment
import com.example.qddapp.databinding.FragmentCrearBinding
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
import java.time.LocalDate

class FragmentCrear : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCrearBinding
    private var olddate = "dd/mm/yyyy"
    private var oldtime = "00:00"
    private lateinit var viewModel: MyViewModel
    private var latitudEvento: String = ""
    private var longitudEvento: String = ""
    private var evento: String = ""
    private lateinit var marker: Marker
    private lateinit var circulo : Circle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCrearBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.dataPositionCreate.observe(this, Observer<LatLng> { datos ->
            latitudEvento = datos.latitude.toString()
            longitudEvento = datos.longitude.toString()
        })

        binding.seleccionaCategoria.setOnClickListener {
            FragmentCategorias().show(childFragmentManager, "showPopUp")
        }

        binding.crearEvento.setOnClickListener {
            validation(binding.tituloEvento.text.toString(), binding.inicioCrear.text.toString(), binding.finCrear.text.toString(), binding.descripcion.text.toString(), binding.abiertoEvento.isChecked, "Localicacion Predefinida", latitudEvento, longitudEvento)
        }

        binding.inicioCrear.setOnClickListener {
            showTimePickerDialog(binding.inicioCrear)
        }

        binding.finCrear.setOnClickListener {
            showTimePickerDialog(binding.finCrear)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_HYBRID //El tipo del mapa se vuelve hibrido
        map.isTrafficEnabled = true

        if (!latitudEvento.isEmpty() || !longitudEvento.isEmpty()){
            val marcador = MarkerOptions()
                .position(LatLng(latitudEvento.toDouble(), longitudEvento.toDouble()))
                .title("Usted esta aqui")
                .snippet("Arrastre para colocarse en otro lugar")
                .flat(true)
                .draggable(true) //Nos permite mover los marcadores si lo dejamos pulsado
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            marker = map.addMarker(marcador)!!
            marker?.tag = "localizacion"
            AñadirCirculo(marker, map)
        }

        map.setOnMapClickListener {
            findNavController().navigate(R.id.action_crear_to_fragmentMapa)
        }
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

    fun validation(titulo: String, fecha_inicio: String, fecha_fin: String, descripcion: String, abierto: Boolean, location: String, latitud: String, longitud: String) {
        val myApp = (requireActivity().application as MyApp)

        if (titulo.isEmpty()) {
            binding.tituloEvento.error = "Por favor, escriba el nombre del evento"
            return
        }

        if (evento.isEmpty()){
            binding.errorCategoria.visibility = View.VISIBLE
            evento = "1"
            return
        } else {
            binding.errorCategoria.visibility = View.GONE
        }

        if (fecha_inicio.isEmpty() || fecha_inicio == "yyyy/mm/dd 00:00") {
            binding.inicioCrear.error = "Por favor, ponga la fecha de inicio de evento"
            return
        }

        if (fecha_fin.isEmpty() || fecha_fin == "yyyy/mm/dd 00:00") {
            binding.finCrear.error = "Por favor, ponga la fecha de fin de evento"
            return
        }

        if (descripcion.isEmpty()){
            binding.descripcion.error = "Por favor, escriba la descripcion del evento"
            return
        }

        if (latitud.isEmpty() || longitud.isEmpty()){
            binding.errorMapa.visibility = View.VISIBLE
            return
        }

        myApp.datos.guardarEvento(titulo, fecha_inicio, fecha_fin, descripcion, abierto, location, latitud, longitud, evento.toInt())
        FragmentFotoEvento().show(childFragmentManager, "showPopUpFoto")
    }

    private fun showTimePickerDialog(editText: EditText) {
        val timePicker = TimePickerFragment {onTimeSelected(it, editText)}
        timePicker.show(childFragmentManager, "time")
    }

    private fun onDateSelected(date: String, editText: EditText){
        olddate = date
        editText.setText("$date $oldtime")
    }

    private fun onTimeSelected(time:String, editText: EditText){
        oldtime = time
        editText.setText("$olddate $time")
        val datePicker = DatePickerFragment {onDateSelected(it, editText)}
        datePicker.show(childFragmentManager, "date")
    }
}