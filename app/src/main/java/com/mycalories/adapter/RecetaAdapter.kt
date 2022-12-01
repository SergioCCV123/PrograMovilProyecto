package com.mycalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recetas.ui.receta.RecetaFragmentDirections
import com.mycalories.databinding.RecetaFilaBinding
import com.mycalories.model.Receta

class RecetaAdapter: RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>(){

    inner class RecetaViewHolder(private val itemBinding: RecetaFilaBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(receta: Receta){
            itemBinding.tvNombrereceta.text = receta.nombrereceta
            itemBinding.tvContenidoreceta.text = receta.contenidoreceta

            itemBinding.vistaFila.setOnClickListener{
                //creo una action para navegar al update lugar pasando un argumento lugar
                val action =  RecetaFragmentDirections.actionNavRecetaToUpdateRecetaFragment(receta)
                //efectivamente se para el fragmento....
                itemView.findNavController().navigate(action)
            }

        }

    }

    //lista donde estan los objetos receta a dibujarse
    private var listaRecetas = emptyList<Receta>()


    //Esta funcion crea "cajitas" para cada lugar.. en memoria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val itemBinding = RecetaFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return RecetaViewHolder(itemBinding)
    }

    //Eswta funcion toma un lugar y lo envia a dibujar
    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val receta = listaRecetas[position]
        holder.dibuja(receta)
    }

    //Esta funcion devuelve la cantidad de elementos a dibujar
    override fun getItemCount(): Int {
        return listaRecetas.size
    }

    fun setListaRecetas(recetas: List<Receta>){
        this.listaRecetas = recetas
        notifyDataSetChanged()
    }

}