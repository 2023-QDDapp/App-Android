package com.example.qddapp.UI.popUp

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.qddapp.FragmentError
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.MyApp
import com.example.qddapp.MyViewModel
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentCategoriasBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentCategorias : DialogFragment() {

    private lateinit var binding:FragmentCategoriasBinding
    private lateinit var chip: Chip
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        val miRepositorio = (requireActivity().application as MyApp).repositorio
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.dameTodasCategorias()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let {
                        rellenarChip(respuesta)
                    }
                } else {
                    FragmentError().show(childFragmentManager, "Tag")
                }
            }
        }

        binding.cerrarCategorias.setOnClickListener {
            dismiss()
        }

        binding.siguienteCategorias.setOnClickListener {
            if(binding.chipGroup.checkedChipIds.size !== 1) {
                binding.errorCategorias.visibility = View.VISIBLE
            } else {
                val myApp = (requireActivity().application as MyApp)
                myApp.datos.guardarCategoriaBuscar(binding.chipGroup.checkedChipIds[0])
                dismiss()
            }
        }
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun rellenarChip(categorias: List<Categoria>) {
        for (categoria in categorias) {
            chip = Chip(context)
            chip.text = categoria.categoria
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
            chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
            chip.isCheckable = true
            chip.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(chip: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        (chip as Chip).chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.color_principal))
                        chip.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                        viewModel.updateDataCategory(categoria)
                    } else {
                        (chip as Chip).chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.boton))
                        chip.setTextColor(ContextCompat.getColor(context!!, R.color.color_principal))
                        viewModel.deleteDataCategory()
                    }
                }
            })
            binding.chipGroup.addView(chip)
        }
    }
}