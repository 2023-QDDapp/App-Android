package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.MyApp
import com.example.qddapp.MyViewModel
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentBuscarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentBuscar : Fragment() {

    private var adapter: EventosAdapter? = null
    private lateinit var binding: FragmentBuscarBinding
    private var pullToRefreshWorking = false
    private lateinit var viewModel: MyViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBuscarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getEventos(null)

        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.dataToPass.observe(this, Observer<String> { datos ->

            val data = datos.split(",")

            getEventos(data)

            binding.swipe.setOnRefreshListener {
                binding.busqueda.setQuery("", false)
                binding.busqueda.clearFocus()
                binding.swipe.isRefreshing = true
                pullToRefreshWorking = true
                getEventos(data)
            }
        })

        binding.busqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.filter?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (adapter != null)
                adapter?.filter?.filter(newText)
                return true
            }
        })

        binding.busqueda.setOnCloseListener {
            adapter?.filter?.filter("")
            true
        }

        binding.filtro.setOnClickListener {
            findNavController().navigate(R.id.action_buscar_to_fragmentFiltroBusqueda)
        }
    }

    private fun getEventos(data: List<String>?) {

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        Log.d("datos", data.toString())

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameEventosFiltrados(data?.get(0), data?.get(1), data?.get(2), null, null, null)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    binding.swipe.isRefreshing = false
                    val respuesta = response.body()
                    respuesta?.let {
                        if (pullToRefreshWorking) {
                            pullToRefreshWorking = false
                            refreshRecycler(it)
                        } else {
                            configRecycler(it as ArrayList<Evento>)
                        }
                    }
                } else {
                    binding.swipe.isRefreshing = false
                    Toast.makeText(
                        requireContext(),
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun configRecycler(listaEventos: ArrayList<Evento>) {
        val myApp = (requireActivity().application as MyApp)
        val recyclerView = binding.recyclerView
        adapter = EventosAdapter(listaEventos, myApp.datos.sacarUserId(), null)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun refreshRecycler(listaEventos: List<Evento>) {
        adapter?.refreshList(listaEventos as ArrayList<Evento>)
    }
}