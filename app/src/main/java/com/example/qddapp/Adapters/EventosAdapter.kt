package com.example.qddapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.R
import com.example.qddapp.databinding.EventoBinding

class EventosAdapter(val eventos: List<Evento>) : RecyclerView.Adapter<EventosAdapter.MiCelda>(), Filterable {

    private var eventosCopia = eventos
    inner class MiCelda(val binding: EventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val eventos: Evento = eventos.get(position)

        Log.d("evento", eventos.toString()) //No se porque pero categoria me viene a null, pero la api viene bien

        holder.binding.tagEvento.text = "#" + eventos.categoria
        holder.binding.tituloEvento.text = eventos.descripcion
        holder.binding.fechaComletaEvento.text = formatFecha(eventos.fechaHoraInicio)
        holder.binding.edadUsuario.text = eventos.edad.toString()
        holder.binding.nombreUsuario.text = eventos.organizador
        Glide.with(holder.itemView).load(eventos.fotoOrganizador).into(holder.binding.imagenUsuario)
        Glide.with(holder.itemView).load(eventos.imagenEvento).into(holder.binding.imagenEvento)

        if (eventos.imagenEvento == null) {
            holder.binding.imagenEvento.visibility = View.GONE
        } else {
            Glide.with(holder.itemView).load(eventos.imagenEvento).into(holder.binding.imagenEvento)
        }

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_inicio_to_fragmentDetalleEvento)
        }
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    fun refreshList(listaEventos: ArrayList<Evento>) {
        eventosCopia = listaEventos
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val palabraABuscar = p0.toString()

                if (palabraABuscar.isEmpty()) {
                    eventosCopia = eventos
                } else {
                    eventosCopia = eventos.filter {
                        (it.organizador.lowercase().contains(palabraABuscar.lowercase()))
                    } as ArrayList<Evento>
                }
                val filterResults = FilterResults()
                filterResults.values = eventosCopia
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                eventosCopia = p1?.values as ArrayList<Evento>
                notifyDataSetChanged()
            }
        }
    }
}