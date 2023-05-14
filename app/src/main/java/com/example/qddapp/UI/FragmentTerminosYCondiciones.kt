package com.example.qddapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qddapp.databinding.FragmentTerminosYCondicionesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentTerminosYCondiciones : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTerminosYCondicionesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTerminosYCondicionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cerrarTerminos.setOnClickListener {
            dismiss()
        }
    }
}