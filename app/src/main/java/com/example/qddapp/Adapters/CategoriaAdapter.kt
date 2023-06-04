package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.MyApp
import com.example.qddapp.databinding.CategoriaBinding

class CategoriaAdapter(val categoryList: ArrayList<Categoria>) : RecyclerView.Adapter<CategoriaAdapter.MiCelda>(), Filterable {

    private var categoriasCopia = ArrayList<Categoria>()

    init {
        categoriasCopia.addAll(categoryList)
    }

    inner class MiCelda(val binding: CategoriaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoriaBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: CategoriaAdapter.MiCelda, position: Int) {
        val categoria: Categoria = categoriasCopia[position]
        holder.binding.categoria.text = categoria.categoria

        /* NO VA Y ME CAGO EN TODO */
//        Lo unico que he encontrado es esto: https://stackoverflow.com/questions/73936737/android-requireactivity-on-adapter

//        val myApp = (requireActivity().application as MyApp)

        /* No se porque pero no me funciona el setOnClickListener*/
        holder.itemView.setOnClickListener {
//            myApp.datos.guardarCategoria(position)
        }
    }

    override fun getItemCount(): Int {
        return categoriasCopia.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val palabraABuscar = p0.toString()

                if (palabraABuscar.isEmpty()) {
                    categoriasCopia.addAll(categoryList)
                } else {
                    categoriasCopia = categoryList.filter {
                        (it.categoria.lowercase().contains(palabraABuscar.lowercase()))
                    } as ArrayList<Categoria>
                }
                val filterResults = FilterResults()
                filterResults.values = categoriasCopia
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                categoriasCopia = p1?.values as ArrayList<Categoria>
                notifyDataSetChanged()
            }
        }
    }
}