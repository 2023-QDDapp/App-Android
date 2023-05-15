package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentBuscarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentBuscar : Fragment() {

    private lateinit var adapter: EventosAdapter
    private lateinit var binding: FragmentBuscarBinding
    private var pullToRefreshWorking = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBuscarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getEventos()

        binding.swipe.setOnRefreshListener {
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
            binding.swipe.isRefreshing = true
            pullToRefreshWorking = true
            getEventos()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        binding.searchView.setOnCloseListener {
            adapter.filter.filter("")
            true
        }
    }

    private fun getEventos() {
        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameTodosEventos()
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
        val recyclerView = binding.recyclerView
        adapter = EventosAdapter(listaEventos)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun refreshRecycler(listaEventos: List<Evento>) {

        adapter.refreshList(listaEventos as ArrayList<Evento>)
    }
}