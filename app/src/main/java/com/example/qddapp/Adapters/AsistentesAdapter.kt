package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.databinding.AsistenteBinding

class AsistentesAdapter(val usuario: List<Asistente>) : RecyclerView.Adapter<AsistentesAdapter.MiCelda>() {

    inner class MiCelda(val binding: AsistenteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AsistentesAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AsistenteBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: AsistentesAdapter.MiCelda, position: Int) {
        val usuario: Asistente = usuario.get(position)

        holder.binding.nombreUsuarioAsistente.text = usuario.nombre
        Glide.with(holder.itemView).load(usuario.foto).into(holder.binding.fotoUsuarioAsistente)
    }

    override fun getItemCount(): Int {
        return usuario.size
    }
}