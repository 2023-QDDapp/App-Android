package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.databinding.FragmentSolicitudEnviadaBinding

class FragmentSolicitudEnviada : DialogFragment() {

    private lateinit var binding: FragmentSolicitudEnviadaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSolicitudEnviadaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        arguments?.let {
            val mensaje = it.getString("mensaje")
            binding.textView34.text= mensaje
        }

        binding.cerrarSolicitud.setOnClickListener {
            dismiss()
            findNavController().navigateUp()
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