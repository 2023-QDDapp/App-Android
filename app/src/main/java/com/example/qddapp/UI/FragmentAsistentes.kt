package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.AsistentesAdapter
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentAsistentesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAsistentes : Fragment() {

    private lateinit var binding: FragmentAsistentesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAsistentesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameElEvento()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let { configRecycler(respuesta.asistentes) }
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

    private fun configRecycler(listaAsistente: List<Asistente>?) {
        val recyclerView = binding.recyclerViewAsistentes
        val adapter = listaAsistente?.let { AsistentesAdapter(it) }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}