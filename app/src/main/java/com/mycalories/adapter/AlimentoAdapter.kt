package com.mycalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mycalories.databinding.AlimentoFilaBinding
import com.mycalories.model.Alimento
import com.mycalories.ui.alimentos.AlimentoFragmentDirections

class AlimentoAdapter : RecyclerView.Adapter<AlimentoAdapter.AlimentoViewHolder>(){

    inner class AlimentoViewHolder(private val itemBinding: AlimentoFilaBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(alimento: Alimento){
            itemBinding.tvAlimento.text = alimento.alimento
            itemBinding.tvCalorias.text = alimento.calorias

            itemBinding.vistaFila.setOnClickListener{
                //creo una action para navegar al update lugar pasando un argumento lugar
                val action =  AlimentoFragmentDirections.actionNavAlimentoToUpdateAlimentoFragment(alimento)

                //efectivamente se para el fragmento....
                itemView.findNavController().navigate(action)
            }

        }

    }

    //lista donde estan los objetos lugar a dibujarse
    private var listaAlimentos = emptyList<Alimento>()


    //Esta funcion crea "cajitas" para cada lugar.. en memoria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val itemBinding = AlimentoFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return AlimentoViewHolder(itemBinding)
    }

    //Eswta funcion toma un lugar y lo envia a dibujar
    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val alimento = listaAlimentos[position]
        holder.dibuja(alimento)
    }

    //Esta funcion devuelve la cantidad de elementos a dibujar
    override fun getItemCount(): Int {
        return listaAlimentos.size
    }

    fun setListaAlimentos(alimentos: List<Alimento>){
        this.listaAlimentos = alimentos
        notifyDataSetChanged()
    }

}