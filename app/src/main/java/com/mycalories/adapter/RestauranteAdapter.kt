package com.mycalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mycalories.databinding.RestauranteFilaBinding
import com.mycalories.model.Restaurante
import com.mycalories.ui.restaurantes.RestaurantesFragmentDirections

class RestauranteAdapter : RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>(){

    inner class RestauranteViewHolder(private val itemBinding: RestauranteFilaBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(restaurante: Restaurante){
            itemBinding.tvNombreRestaurante.text = restaurante.nombre
            itemBinding.tvTelefono.text = restaurante.telefono

            itemBinding.vistaFila.setOnClickListener{
                val action =  RestaurantesFragmentDirections.actionNavRestaurantesToUpdateRestauranteFragment(restaurante)
                itemView.findNavController().navigate(action)
            }

        }

    }

    //lista donde estan los objetos lugar a dibujarse
    private var listaRestaurante = emptyList<Restaurante>()


    //Esta funcion crea "cajitas" para cada lugar.. en memoria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val itemBinding = RestauranteFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return RestauranteViewHolder(itemBinding)
    }

    //Eswta funcion toma un lugar y lo envia a dibujar
    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = listaRestaurante[position]
        holder.dibuja(restaurante)
    }

    //Esta funcion devuelve la cantidad de elementos a dibujar
    override fun getItemCount(): Int {
        return listaRestaurante.size
    }

    fun setListaAlimentos(restaurantes: List<Restaurante>){
        this.listaRestaurante = restaurantes
        notifyDataSetChanged()
    }

}