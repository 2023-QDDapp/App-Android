package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.MyApp
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var miRepositorio: Repositorio

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        miRepositorio = (requireActivity().application as MyApp).repositorio
        getEventos()

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                getEventos(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun configRecycler(listaEventos: List<Evento>) {
        val recyclerView = binding.recyclerview
        val adapter = EventosAdapter(listaEventos as ArrayList<Evento>)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    fun getEventos(id: Int? = null) {

        val myApp = (requireActivity().application as MyApp)

        when (id) {
            1 ->
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameEventosSeguidos(myApp.datos.sacarUserId())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let { configRecycler(respuesta) }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            else ->
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameEventosParaMi(myApp.datos.sacarUserId())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let { configRecycler(respuesta) }
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
    }
}