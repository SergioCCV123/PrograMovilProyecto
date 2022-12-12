package com.mycalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mycalories.databinding.AlimentoFilaBinding
import com.mycalories.databinding.RutinaFilaBinding

import com.mycalories.model.Alimento
import com.mycalories.model.Rutinas
import com.mycalories.ui.alimentos.AlimentoFragmentDirections
import com.mycalories.ui.rutinas.RutinasFragmentDirections

class RutinaAdapter : RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    inner class RutinaViewHolder(private val itemBinding: RutinaFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun dibuja(rutinas: Rutinas) {
            itemBinding.tvRnombre.text = rutinas.nombre
            itemBinding.tvEjeryreps.text = rutinas.EjeryReps
            itemBinding.tvRepaso.text = rutinas.reposo

            itemBinding.vistaRutina.setOnClickListener {
                val action =
                    RutinasFragmentDirections.actionRutinasFragmentToUpdateRutinasFragment(rutinas)

                itemView.findNavController().navigate(action)
            }

        }

    }

    private var listaRutinas = emptyList<Rutinas>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val itemBinding = RutinaFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return RutinaViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val rutina = listaRutinas[position]
        holder.dibuja(rutina)
    }
    override fun getItemCount(): Int {
        return listaRutinas.size
    }
    fun setListaRutinas(rutinas: List<Rutinas>){
        this.listaRutinas = rutinas
        notifyDataSetChanged()
    }
}

