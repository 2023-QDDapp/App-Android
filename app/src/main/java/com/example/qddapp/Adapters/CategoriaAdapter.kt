package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.Modelos.Evento
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
    }

    override fun getItemCount(): Int {
        return categoriasCopia.size
    }

    fun refreshList(listaCategorias: ArrayList<Categoria>) {
        categoriasCopia .clear()
        categoriasCopia.addAll(listaCategorias)
        categoryList.clear()
        categoryList.addAll(listaCategorias)
        notifyDataSetChanged()
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