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
import com.example.qddapp.databinding.FragmentPermisosBinding

class FragmentPermisos : DialogFragment() {

    private lateinit var binding: FragmentPermisosBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPermisosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        /** Navegación **/

        binding.siemprePermiso.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentPreferencias_to_fragmentPantallaCarga)
            dismiss()
        }

        binding.encendidaPermisos.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentPreferencias_to_fragmentPantallaCarga)
            dismiss()
        }

        binding.noPermisos.setOnClickListener {
            val permisos2PopUp = FragmentPermisos2()
            permisos2PopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
            dismiss()
        }

        binding.terminosPermisos.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.cerrarPermisos.setOnClickListener {
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