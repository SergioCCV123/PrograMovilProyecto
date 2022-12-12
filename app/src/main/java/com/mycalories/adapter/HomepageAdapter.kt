package com.mycalories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycalories.databinding.HomepageFilaBinding
import com.mycalories.model.Homepage
import com.mycalories.ui.home.HomepageFragmentDirections

class HomepageAdapter : RecyclerView.Adapter<HomepageAdapter.HomepageViewHolder>(){

    inner class HomepageViewHolder(private val itemBinding: HomepageFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(homepage: Homepage){
            itemBinding.tvTitulo.text = homepage.Titulo

            Glide.with(itemBinding.root.context)
                .load(homepage.rutaImagen)
                .circleCrop()
                .into(itemBinding.imagen)

            itemBinding.vistaFila.setOnClickListener {
                val action = HomepageFragmentDirections.actionNavHomeToNewsViewFragment(homepage)
                itemView.findNavController().navigate(action)
            }
        }
    }

    private var listaHomepage = emptyList<Homepage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomepageViewHolder {
        val itemBinding = HomepageFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomepageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomepageViewHolder, position: Int) {
        val homepage = listaHomepage[position]
        holder.dibuja(homepage)
    }

    override fun getItemCount(): Int {
        return listaHomepage.size
    }

    fun setListaHomepage(homepage : List<Homepage>){
        this.listaHomepage = homepage
        notifyDataSetChanged()
    }

}