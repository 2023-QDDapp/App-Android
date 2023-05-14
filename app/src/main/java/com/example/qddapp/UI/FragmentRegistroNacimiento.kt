package com.example.qddapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentRegistroNacimientoBinding
import java.util.Calendar

class FragmentRegistroNacimiento : Fragment() {

    private lateinit var binding: FragmentRegistroNacimientoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroNacimientoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nombre.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistroNombre)
        }

        binding.siguienteFechaNacimiento.setOnClickListener {
            binding.datepicker

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, binding.datepicker.dayOfMonth)
            calendar.set(Calendar.MONTH, binding.datepicker.month)
            calendar.set(Calendar.YEAR, binding.datepicker.year)

            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
            val timestamp = calendar.timeInMillis/100
            Log.d("FECHA", timestamp.toString())
            Toast.makeText(requireContext(), timestamp.toString(), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_fragmentRegistroNacimiento_to_fragmentRegistroTelefono)
        }
    }
}