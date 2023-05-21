package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.R
import com.example.qddapp.databinding.AsistenteBinding

class AsistentesAdapter(val usuario: List<Asistente>) : RecyclerView.Adapter<AsistentesAdapter.MiCelda>() {

    inner class MiCelda(val binding: AsistenteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AsistentesAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AsistenteBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val usuario: Asistente = usuario.get(position)

        holder.binding.nombreUsuarioAsistente.text = usuario.nombre
        Glide.with(holder.itemView).load(usuario.foto).into(holder.binding.fotoUsuarioAsistente)

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.perfil)
        }
    }

    override fun getItemCount(): Int {
        return usuario.size
    }
}