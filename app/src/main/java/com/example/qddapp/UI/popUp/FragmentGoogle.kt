package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.UI.popUp.FragmentTerminosYCondiciones
import com.example.qddapp.databinding.FragmentGoogleBinding

class FragmentGoogle : DialogFragment() {

    private lateinit var binding: FragmentGoogleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGoogleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        binding.usuario1.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
            dismiss()
        }
        binding.usuario2.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
            dismiss()
        }
        binding.usuario3.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
            dismiss()
        }
        binding.anadirUsuario.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistroNombre)
            dismiss()
        }
        binding.terminosGoogle.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.cerrarGoogle.setOnClickListener {
            dismiss()
        }
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