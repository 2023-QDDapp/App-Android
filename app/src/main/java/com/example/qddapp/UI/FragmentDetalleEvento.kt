package com.example.qddapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Retrofit.Repositorio
import com.example.qddapp.databinding.FragmentDetalleEventoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentDetalleEvento : Fragment() {

    private lateinit var binding: FragmentDetalleEventoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetalleEventoBinding.inflate(inflater, container, false)
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
                    respuesta?.let {
                        rellenarDatos(respuesta)
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

    fun rellenarDatos(evento : Evento) {
        Glide.with(this).load(evento.imagenEvento).into(binding.fotoDetalleEvento)
        binding.nombreDetalleEvento.text = evento.descripcion
        Glide.with(this).load(evento.fotoOrganizador).into(binding.fotoPerfilOrganizadorDetalleEvento)
        binding.nombreOrganizadorDetalleEvento.text = evento.organizador
        binding.edadDetalleEvento.text = evento.edad.toString() + " años"
        Glide.with(this).load(evento.asistentes?.get(0)?.foto).into(binding.fotoPerfilDetalleEvento2)
        Glide.with(this).load(evento.asistentes?.get(1)?.foto).into(binding.fotoPerfilDetalleEvento3)
        binding.descripcionDetalleEvento.text = evento.descripcion
        binding.localizacionDetalleEvento.text = evento.location
    }
}