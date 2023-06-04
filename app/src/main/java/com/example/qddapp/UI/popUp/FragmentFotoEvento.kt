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
import com.example.qddapp.Modelos.Evento
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
        val foto = "asd"

        binding.continuarFotoEvento.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = miRepositorio.crearEvento(
                    myApp.datos.sacarTituloEvento(),
                    myApp.datos.sacarFechaInicioEvento(),
                    myApp.datos.sacarFechaFinEvento(),
                    myApp.datos.sacardescripcionEvento(),
                    foto,
                    myApp.datos.sacarTipoEvento(),
                    myApp.datos.sacarLocationEvento(),
                    myApp.datos.sacarLatitudEvento(),
                    myApp.datos.sacarLongitudEvento(),
                    myApp.datos.sacarCategoriaEvento()
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.code() == 200) {
                        val respuesta = response.body()
                        respuesta?.let {
                            val bundle = bundleOf("mensaje" to it.mensaje)
                            FragmentEventoPublicado().arguments = bundle
                            FragmentEventoPublicado().show(childFragmentManager, "Tag")
                            dismiss()
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