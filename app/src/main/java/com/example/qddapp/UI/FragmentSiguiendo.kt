package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.Adapters.AsistentesAdapter
import com.example.qddapp.Adapters.EventosAdapter
import com.example.qddapp.Adapters.SiguiendoAdapter
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.MyApp
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentSiguiendoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSiguiendo : Fragment() {

    private lateinit var binding: FragmentSiguiendoBinding
    lateinit var miRepositorio: Repositorio
    private lateinit var adapter: SiguiendoAdapter

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

        binding.atrasSeguidos.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun configRecycler(listaAsistente: List<Asistente>?) {
        val recyclerView = binding.recyclerViewAsistentesSiguiendo
        adapter = SiguiendoAdapter(listaAsistente as ArrayList<Asistente>, 1, object : SiguiendoAdapter.MyClickListener{
            override fun onItemClickListener(asistente: Asistente, position: Int) {
                miRepositorio = (requireActivity().application as MyApp).repositorio
                CoroutineScope(Dispatchers.IO).launch {
                    val respuesta = miRepositorio.dejarSeguirUsuario(asistente.id)

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
}