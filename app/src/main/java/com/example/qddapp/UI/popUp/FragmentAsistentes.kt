package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.qddapp.Adapters.AsistentesAdapter
import com.example.qddapp.FragmentError
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentAsistentesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAsistentes : DialogFragment() {

    private lateinit var binding: FragmentAsistentesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAsistentesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWidthPercent(90)

        val miRepositorio = (requireActivity().application as MyApp).repositorio

        arguments?.let {
            val id = it.getInt("eventoId")
            Log.d("idEvento", id.toString())
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.dameElEvento(id)
                Log.d("response", response.toString())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            configRecycler(respuesta.asistentes)
                            Glide.with(this@FragmentAsistentes).load(respuesta.fotoOrganizador).into(binding.fotoOrganizador)
                            binding.nombreOrganizador.text = respuesta.organizador
                        }
                    } else {
                        FragmentError().show(childFragmentManager, "Tag")
                    }
                }
            }
        }

        binding.cerrarAsistentes.setOnClickListener {
            dismiss()
        }
    }

    private fun configRecycler(listaAsistente: List<Asistente>?) {
        val recyclerView = binding.recyclerViewAsistentes
        val adapter = AsistentesAdapter(listaAsistente as ArrayList<Asistente>, object : AsistentesAdapter.MyClickListener {
            override fun onItemClickListener(bundle: Bundle) {
                findNavController().navigate(R.id.fragmentPerfilUsuario, bundle)
            }
        })
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun DialogFragment.setFullScreen() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}