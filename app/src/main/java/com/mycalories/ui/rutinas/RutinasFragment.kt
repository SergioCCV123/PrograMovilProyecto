package com.mycalories.ui.rutinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.RutinaAdapter
import com.mycalories.databinding.FragmentRutinasBinding
import com.mycalories.viewmodel.RutinasViewModel

class RutinasFragment : Fragment() {

    private var _binding: FragmentRutinasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rutinasViewModel =
            ViewModelProvider(this)[RutinasViewModel::class.java]

        _binding = FragmentRutinasBinding.inflate(inflater, container, false)

        binding.addRutinasFabButton.setOnClickListener{
            findNavController().navigate(R.id.action_rutinasFragment_to_addRutinasFragment)
        }


        val rutinaAdapter = RutinaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = rutinaAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        rutinasViewModel.getRutinas.observe(viewLifecycleOwner){
                rutinas -> rutinaAdapter.setListaRutinas(rutinas)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}