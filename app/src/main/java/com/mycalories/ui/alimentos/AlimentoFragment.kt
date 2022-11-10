package com.mycalories.ui.alimentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.AlimentoAdapter
import com.mycalories.databinding.FragmentAlimentoBinding
import com.mycalories.viewmodel.AlimentoViewModel

class AlimentoFragment : Fragment() {

    private var _binding: FragmentAlimentoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val alimentoViewModel =
            ViewModelProvider(this)[AlimentoViewModel::class.java]

        _binding = FragmentAlimentoBinding.inflate(inflater, container, false)

        binding.addAlimentoFabButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_alimento_to_addAlimentoFragment2)
        }

        //se genera el recicler view para ver la informacion
        val alimentoAdapter = AlimentoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = alimentoAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        alimentoViewModel.getAlimentos.observe(viewLifecycleOwner){
                alimentos -> alimentoAdapter.setListaAlimentos(alimentos)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}