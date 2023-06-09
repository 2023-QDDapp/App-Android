package com.example.qddapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.EventoMiEvento
import com.example.qddapp.R
import com.example.qddapp.databinding.EventoBinding

class MiEventoAdapter(val eventos: ArrayList<EventoMiEvento>, val position: Int, val listener: MyClickListener) : RecyclerView.Adapter<MiEventoAdapter.MiCelda>() {

    inner class MiCelda(val binding: EventoBinding) : RecyclerView.ViewHolder(binding.root)

    interface MyClickListener {
        fun onItemClickListener(evento: EventoMiEvento, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MiEventoAdapter.MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiEventoAdapter.MiCelda, position: Int) {
        val evento: EventoMiEvento = eventos[position]

        holder.binding.tituloEvento.text = evento.titulo
        Glide.with(holder.itemView).load(evento.fotoOrganizador).into(holder.binding.imagenUsuario)
        Glide.with(holder.itemView).load(evento.imagenEvento).into(holder.binding.imagenEvento)
        holder.binding.fechaComletaEvento.text = formatFecha(evento.fechaHoraInicio!!)
        holder.binding.tagEvento.text = "#${evento.categoria}"

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.perfil)
        }

        holder.binding.borrar.setOnClickListener {
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

    fun formatFecha(fecha: String) : String {
        var fechaTransformada = explode("-", fecha).toMutableList()
        val variante = explode(" ", fechaTransformada[2])
        fechaTransformada = fechaTransformada.dropLast(1) as MutableList<String>

        fechaTransformada = fechaTransformada.plus(variante[0]) as MutableList<String>
        fechaTransformada = fechaTransformada.plus(variante[1]) as MutableList<String>

        var horaTransformada = explode(":", fechaTransformada[3])

        when(fechaTransformada[1]) {
            "01" -> {
                fechaTransformada[1] = "Enero"
            }
            "02" -> {
                fechaTransformada[1] = "Febrero"
            }
            "03" -> {
                fechaTransformada[1] = "Marzo"
            }
            "04" -> {
                fechaTransformada[1] = "Abril"
            }
            "05" -> {
                fechaTransformada[1] = "Mayo"
            }
            "06" -> {
                fechaTransformada[1] = "Junio"
            }
            "07" -> {
                fechaTransformada[1] = "Julio"
            }
            "08" -> {
                fechaTransformada[1] = "Agosto"
            }
            "09" -> {
                fechaTransformada[1] = "Septiembre"
            }
            "10" -> {
                fechaTransformada[1] = "Octubre"
            }
            "11" -> {
                fechaTransformada[1] = "Noviembre"
            }
            "12" -> {
                fechaTransformada[1] = "Diciembre"
            }
        }
        return fechaTransformada[2] + " de " + fechaTransformada[1] + " del " + fechaTransformada[0] + " a las " + horaTransformada[0] + ":" + horaTransformada[1]
    }
    fun explode(delimiter: String, input: String): List<String> {
        return input.split(delimiter)
    }
}