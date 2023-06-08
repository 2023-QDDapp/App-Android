package com.example.qddapp.UI

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.MyViewModel
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.DatePickerFragment
import com.example.qddapp.UI.popUp.FragmentCategorias
import com.example.qddapp.UI.popUp.TimePickerFragment
import com.example.qddapp.databinding.FragmentFiltroBusquedaBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.chip.Chip

class FragmentFiltroBusqueda : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentFiltroBusquedaBinding
    private var olddate = "dd/mm/yyyy"
    private var oldtime = "00:00"
    private lateinit var viewModel: MyViewModel
    private lateinit var nombreCategoria: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFiltroBusquedaBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.busquedaFiltro.setOnClickListener {
            FragmentCategorias().show(childFragmentManager, "TAG")
        }

        binding.inicioFiltro.setOnClickListener {
            showTimePickerDialog(binding.inicioFiltro)
        }

        binding.finFiltro.setOnClickListener {
            showTimePickerDialog(binding.finFiltro)
        }

        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.dataToPassCategory.observe(this, Observer<Categoria> { category ->
            if (category.id > 1 && binding.chipGroupFiltro.size == 0) {
                val chip = Chip(context)
                chip.text = category.categoria
                chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
                binding.chipGroupFiltro.addView(chip)
            } else if (binding.chipGroupFiltro.size > 0){
                binding.chipGroupFiltro.removeAllViews()
                val chip = Chip(context)
                chip.text = category.categoria
                chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
                binding.chipGroupFiltro.addView(chip)
            }
            nombreCategoria = category.categoria
        })

        binding.buscarFiltro.setOnClickListener {
            var filtrado: String? = ""
            val fechaInicio = binding.inicioFiltro.text.toString()
            val fechaFin = binding.finFiltro.text.toString()
//            val tipoEvento = binding.abiertoFiltro.isChecked.toString()
//            val distancia = binding.distanciaFiltro.progress.toString()
            filtrado += "$nombreCategoria,"
            if (!fechaInicio.isEmpty() && fechaInicio != "dd/mm/yyyy 00:00"){
                filtrado += "$fechaInicio,"
            } else {
                filtrado += null + ","
            }

            if (!fechaFin.isEmpty() && fechaFin != "dd/mm/yyyy 00:00"){
                filtrado += "$fechaFin,"
            } else {
                filtrado += null + ","
            }
//            filtrado += "tipoEvento=$tipoEvento,"+
//            filtrado += "distancia=$distancia,"

            Log.d("dato_filtrado", filtrado.toString())

            viewModel.updateData(filtrado.toString())
            findNavController().navigateUp()
        }

        binding.atrasFiltro.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_HYBRID //El tipo del mapa se vuelve hibrido
        map.isTrafficEnabled = true
    }

    private fun showTimePickerDialog(editText: EditText) {
        val timePicker = TimePickerFragment {onTimeSelected(it, editText)}
        val datePicker = DatePickerFragment {onDateSelected(it, editText)}
        datePicker.show(childFragmentManager, "date")
        timePicker.show(childFragmentManager, "time")
    }

    private fun onDateSelected(date: String, editText: EditText){
        olddate = date
        editText.setText("$date $oldtime")
    }

    private fun onTimeSelected(time:String, editText: EditText){
        oldtime = time
        editText.setText("$olddate $time")
    }
}