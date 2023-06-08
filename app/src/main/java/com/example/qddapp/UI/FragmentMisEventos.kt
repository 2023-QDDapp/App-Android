package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Adapters.MiEventoAdapter
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.EventoMiEvento
import com.example.qddapp.MyApp
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentMisEventosBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMisEventos : Fragment() {

    private lateinit var binding: FragmentMisEventosBinding
    lateinit var miRepositorio: Repositorio
    private lateinit var adapter: MiEventoAdapter
    private lateinit var adapterEventos: EventosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMisEventosBinding.inflate(inflater, container, false)
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

        binding.atrasMisEventos.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun getEventos(id: Int? = null) {
        val myApp = (requireActivity().application as MyApp)

        when (id) {
            1 ->
                CoroutineScope(Dispatchers.IO).launch {
                    val response = miRepositorio.dameMiHistorial(myApp.datos.sacarUserId())
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.code() == 200) {
                            val respuesta = response.body()
                            respuesta?.let { configRecyclerEventos(respuesta) }
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
                    val response = miRepositorio.dameMisEventos(myApp.datos.sacarUserId())
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.code() == 200) {
                            val respuesta = response.body()
                            respuesta?.let { configRecycler(respuesta.eventos!!) }
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

    private fun configRecycler(listaEventos: List<EventoMiEvento>) {
        val myApp = (requireActivity().application as MyApp)
        val recyclerView = binding.recyclerViewMisEventos
         adapter = MiEventoAdapter(listaEventos as ArrayList<EventoMiEvento>, myApp.datos.sacarUserId(), object : MiEventoAdapter.MyClickListener{
             override fun onItemClickListener(evento: EventoMiEvento, position: Int) {
                 miRepositorio = (requireActivity().application as MyApp).repositorio
                 CoroutineScope(Dispatchers.IO).launch {
                     val respuesta = miRepositorio.borrarEvento(evento.idEvento!!)

                     withContext(Dispatchers.Main){
                         if(respuesta.isSuccessful){
                             adapter.deleteItem(position)
                         }
                     }
                 }
             }

         })
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun configRecyclerEventos(listaEventos: List<Evento>) {
        val myApp = (requireActivity().application as MyApp)
        val recyclerView = binding.recyclerViewMisEventos
        adapterEventos = EventosAdapter(listaEventos as ArrayList<Evento>, myApp.datos.sacarUserId(), object : EventosAdapter.MyClickListener{
            override fun onItemClickListener(evento: Evento, position: Int) {
                miRepositorio = (requireActivity().application as MyApp).repositorio
                CoroutineScope(Dispatchers.IO).launch {
                    val respuesta = miRepositorio.borrarEvento(evento.idEvento)
                    withContext(Dispatchers.Main){
                        if(respuesta.isSuccessful){
                            adapterEventos.deleteItem(position)
                        }
                    }
                }
            }

        })
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterEventos
    }
}