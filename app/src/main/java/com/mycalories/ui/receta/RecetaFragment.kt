package com.example.recetas.ui.receta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.RecetaAdapter
import com.mycalories.databinding.FragmentRecetaBinding
import com.mycalories.viewmodel.RecetaViewModel

class RecetaFragment : Fragment() {

    private var _binding: FragmentRecetaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recetaViewModel =
            ViewModelProvider(this)[RecetaViewModel::class.java]

        _binding = FragmentRecetaBinding.inflate(inflater, container, false)

        binding.addRecetaFabButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_receta_to_addRecetaFragment)
        }

        //se genera el recicler view para ver la informacion
        val recetaAdapter = RecetaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = recetaAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        recetaViewModel.getRecetas.observe(viewLifecycleOwner){
                recetas -> recetaAdapter.setListaRecetas(recetas)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}