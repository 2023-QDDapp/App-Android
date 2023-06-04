package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.AsistentesAdapter
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.MyApp
import com.example.qddapp.databinding.FragmentSiguiendoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSiguiendo : Fragment() {

    private lateinit var binding: FragmentSiguiendoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSiguiendoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        arguments?.let {
            val id = it.getInt("id_usuario")
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameMisSeguidos(id)
                Log.d("response", response.toString())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            configRecycler(respuesta)
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
    }

    private fun configRecycler(listaAsistente: List<Asistente>?) {
        val recyclerView = binding.recyclerViewAsistentesSiguiendo
        val adapter = listaAsistente?.let { AsistentesAdapter(it) }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}