package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.EventoMiEvento
import com.example.qddapp.R
import com.example.qddapp.databinding.EventoMisEventosBinding

class MiEventoAdapter(val eventos: ArrayList<EventoMiEvento>, val position: Int, val listener: MyClickListener) : RecyclerView.Adapter<MiEventoAdapter.MiCelda>() {

    inner class MiCelda(val binding: EventoMisEventosBinding) : RecyclerView.ViewHolder(binding.root)

    interface MyClickListener {
        fun onItemClickListener(evento: EventoMiEvento, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MiEventoAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventoMisEventosBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiEventoAdapter.MiCelda, position: Int) {
        val evento: EventoMiEvento = eventos[position]

        holder.binding.tituloEventoMisEventos.text = evento.titulo
        Glide.with(holder.itemView).load(evento.imagenEvento).into(holder.binding.imagenEventoMisEventos)

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.perfil)
        }

        holder.binding.eliminarEvento.setOnClickListener {
            listener?.onItemClickListener(evento, position)
        }
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    fun deleteItem(position: Int){
        eventos.removeAt(position)
        notifyDataSetChanged()
    }
}