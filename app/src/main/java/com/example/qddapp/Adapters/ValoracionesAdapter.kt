package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Valoracion
import com.example.qddapp.databinding.ValoracionBinding

class ValoracionesAdapter(val valoracion: List<Valoracion>) : RecyclerView.Adapter<ValoracionesAdapter.MiCelda>() {

    inner class MiCelda(val binding: ValoracionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ValoracionBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val valoracion: Valoracion = valoracion.get(position)

        holder.binding.nombreValoracion.text = valoracion.nombre_usuario
        holder.binding.mensageValoracion.text = valoracion.mensaje
        Glide.with(holder.itemView).load(valoracion.foto).into(holder.binding.fotoValoracion)
    }

    override fun getItemCount(): Int {
        return valoracion.size
    }
}