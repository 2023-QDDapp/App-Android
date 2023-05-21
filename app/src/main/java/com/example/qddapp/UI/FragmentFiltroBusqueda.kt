package com.example.qddapp.UI

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.CategoriaAdapter
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.R
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.UI.popUp.DatePickerFragment
import com.example.qddapp.UI.popUp.TimePickerFragment
import com.example.qddapp.databinding.FragmentFiltroBusquedaBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentFiltroBusqueda : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentFiltroBusquedaBinding
    private lateinit var adapter: CategoriaAdapter
    private var pullToRefreshWorking = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFiltroBusquedaBinding.inflate(inflater, container, false)

        val fragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment //Esto se pone para saber cuando ha cargado o no
        fragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameTodasCategorias()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let {
                        if (pullToRefreshWorking) {
                            pullToRefreshWorking = false
                            refreshRecycler(it)
                        } else {
                            configRecycler(it as ArrayList<Categoria>)
                        }
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

        binding.inicioFiltro.setOnClickListener {
            showTimePickerDialog(binding.inicioFiltro)
        }

        binding.finFiltro.setOnClickListener {
            showTimePickerDialog(binding.finFiltro)
        }

        binding.buscarFiltro.setOnClickListener {
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
        editText.setText("$date 00:00")
    }

    private fun onTimeSelected(time:String, editText: EditText){
        editText.setText("dd/mm/yyyy $time")
    }

    private fun configRecycler(listaCategoria: ArrayList<Categoria>) {
        val recyclerView = binding.recyclerViewFiltro
        adapter = CategoriaAdapter(listaCategoria)
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun refreshRecycler(listaCategoria: List<Categoria>) {
        adapter.refreshList(listaCategoria as ArrayList<Categoria>)
    }

//    fun rellenarChip(categorias: List<Categoria>) {
//        for (categoria in categorias) {
//            val chip = Chip(context)
//            chip.text = categoria.categoria
//            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
//            chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
//            binding.chipGroup.addView(chip)
//        }
//    }
}