package com.example.qddapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.databinding.EventoBinding

class EventosAdapter(val eventos: List<Evento>) : RecyclerView.Adapter<EventosAdapter.MiCelda>() {

    inner class MiCelda(val binding: EventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val eventos: Evento = eventos.get(position)

        holder.binding.tagEvento.text = eventos.categoria
        holder.binding.tituloEvento.text = eventos.name
        holder.binding.fechaComletaEvento.text = formatFecha(eventos.fechaHoraInicio)
        holder.binding.edadUsuario.text = eventos.edad.toString()
        holder.binding.nombreUsuario.text = eventos.name

        Glide.with(holder.itemView).load(eventos.foto).into(holder.binding.imagenUsuario)
        Glide.with(holder.itemView).load(eventos.imagen).into(holder.binding.imagenEvento)

        /*holder.itemView.setOnClickListener {
            val bundle = bundleOf("eventos" to eventos)
            holder.itemView.findNavController().navigate(R.id.)
        }*/
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    fun formatFecha(fecha: String) : String {
        var fechaTransformada = explode("-", fecha).toMutableList() // [2023, 04, 14 09:30:43]
        val variante = explode(" ", fechaTransformada[2]) // [14, 09:30:43]
        fechaTransformada = fechaTransformada.dropLast(1) as MutableList<String> // [2023, 04]

        Log.d("fecha", "1" + fechaTransformada.toString())
        Log.d("fechaVariante", variante.toString())

        fechaTransformada = fechaTransformada.plus(variante[0]) as MutableList<String> // [2023, 04, 14]
        fechaTransformada = fechaTransformada.plus(variante[1]) as MutableList<String> // [2023, 04, 14, 09:30:43]

        Log.d("fecha", "2" + fechaTransformada.toString())

        var horaTransformada = explode(":", fechaTransformada[3])
        Log.d("fecha", "3" + fechaTransformada.toString())

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