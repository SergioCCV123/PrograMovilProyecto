package com.mycalories.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.AlimentoAdapter
import com.mycalories.databinding.FragmentAddRutinasBinding
import com.mycalories.databinding.FragmentRutinasBinding
import com.mycalories.model.Rutinas
import com.mycalories.viewmodel.RutinasViewModel


class AddRutinasFragment : Fragment() {

    private lateinit var rutinasViewModel: RutinasViewModel

    private var _binding: FragmentAddRutinasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rutinasViewModel =
            ViewModelProvider(this)[RutinasViewModel::class.java]

        _binding = FragmentAddRutinasBinding.inflate(inflater, container, false)

        binding.btAddR.setOnClickListener{ addRutina() }

        return binding.root
    }

    private fun addRutina(){
        val nombre=binding.etRutina.text.toString()

        if (nombre.isNotEmpty()){
            val ejeryreps= binding.etEjeryreps.text.toString()
            val reposo = binding.etReposo.text.toString()
            val rutina = Rutinas(0,nombre,ejeryreps,reposo)

            rutinasViewModel.saveRutinas(rutina)
            Toast.makeText(requireContext(),getString(R.string.msg_rutina_added),Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addRutinasFragment_to_rutinasFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}