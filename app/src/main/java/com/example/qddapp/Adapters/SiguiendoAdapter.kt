package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.R
import com.example.qddapp.databinding.PersonaSiguiendoBinding

class SiguiendoAdapter(val usuarios: ArrayList<Asistente>, val position: Int,val listener: MyClickListener) : RecyclerView.Adapter<SiguiendoAdapter.MiCelda>() {

    inner class MiCelda(val binding: PersonaSiguiendoBinding) : RecyclerView.ViewHolder(binding.root)

    interface MyClickListener {
        fun onItemClickListener(asistente: Asistente, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SiguiendoAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PersonaSiguiendoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: SiguiendoAdapter.MiCelda, position: Int) {
        val usuario: Asistente = usuarios[position]

        holder.binding.nombreUsuarioAsistente.text = usuario.nombre
        Glide.with(holder.itemView).load(usuario.foto).into(holder.binding.fotoUsuarioAsistente)

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.perfil)
        }

        holder.binding.eliminarSiguiendo.setOnClickListener {
            listener?.onItemClickListener(usuario, position)
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    fun deleteItem(position: Int){
        usuarios.removeAt(position)
        notifyDataSetChanged()
    }
}