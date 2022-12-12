package com.mycalories.ui.alimentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mycalories.R
import com.mycalories.databinding.FragmentAddAlimentoBinding
import com.mycalories.model.Alimento
import com.mycalories.viewmodel.AlimentoViewModel

class AddAlimentoFragment : Fragment(){

    //objeto que interactua con la tabla
    private  lateinit var alimentoViewModel: AlimentoViewModel

    private var _binding: FragmentAddAlimentoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        alimentoViewModel = ViewModelProvider(this).get(AlimentoViewModel::class.java)

        _binding = FragmentAddAlimentoBinding.inflate(inflater, container, false)

        binding.btAdd.setOnClickListener{ addAlimento() }

        return binding.root
    }

    private fun addAlimento() {
        val nombre=binding.etAlimento.text.toString() //obtienen el texto de lo que el usuario escribio

        if (nombre.isNotEmpty()){
            val calorias=binding.etCalorias.text.toString() //obtienen el texto de lo que el usuario escribio

            val alimento = Alimento(0,nombre,calorias)

            //se procede a registrar el nuevo lugar
            alimentoViewModel.saveAlimento(alimento)
            Toast.makeText(requireContext(),getString(R.string.msg_alimento_added), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addAlimentoFragment_to_nav_alimento)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}