package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.EventoCrear
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentFotoEventoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentFotoEvento : DialogFragment() {

    private lateinit var binding: FragmentFotoEventoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFotoEventoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)
        val myApp = (requireActivity().application as MyApp)
        val miRepositorio = (requireActivity().application as MyApp).repositorio
        val foto = "data:image/jpeg;base64,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"

        val eventoBody = EventoCrear(myApp.datos.sacarTituloEvento(),
            myApp.datos.sacarFechaInicioEvento(),
            myApp.datos.sacarFechaFinEvento(),
            myApp.datos.sacardescripcionEvento(),
            foto,
            myApp.datos.sacarTipoEvento(),
            myApp.datos.sacarLocationEvento(),
            myApp.datos.sacarLatitudEvento(),
            myApp.datos.sacarLongitudEvento(),
            myApp.datos.sacarCategoriaEvento())

        binding.continuarFotoEvento.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.crearEventoBody(eventoBody)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            val bundle = bundleOf("mensaje" to it.mensaje)
                            FragmentEventoPublicado().arguments = bundle
                            FragmentEventoPublicado().show(childFragmentManager, "Tag")
                        }
                    } else {
                        val bundle = bundleOf("mensaje" to response.errorBody())
                        FragmentEventoPublicado().arguments = bundle
                        FragmentEventoPublicado().show(childFragmentManager, "Tag")
                        dismiss()
                    }
                }
            }
        }

        binding.imageView2.setOnClickListener {
            FragmentCamaraGaleria().show(childFragmentManager, "TAG")
        }
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}