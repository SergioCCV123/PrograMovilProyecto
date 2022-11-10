package com.mycalories.ui.alimentos

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mycalories.R
import com.mycalories.databinding.FragmentUpdateAlimentoBinding
import com.mycalories.model.Alimento
import com.mycalories.viewmodel.AlimentoViewModel


class UpdateAlimentoFragment : Fragment() {


    //se define un obketo para obtener los argumentos pasados al fragmento
    private val args by navArgs<UpdateAlimentoFragmentArgs>()

    //objeto que interactuca con la tabla
    private  lateinit var alimentoViewModel: AlimentoViewModel

    private var _binding: FragmentUpdateAlimentoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         alimentoViewModel = ViewModelProvider(this).get(AlimentoViewModel::class.java)

        _binding = FragmentUpdateAlimentoBinding.inflate(inflater, container, false)

        binding.etAlimento.setText(args.alimento.alimento)
        binding.etCalorias.setText(args.alimento.calorias)


        binding.btUpdate.setOnClickListener{ updateAlimento() }
        binding.btDelete.setOnClickListener{ deleteAlimento() }


        return binding.root
    }


    private fun deleteAlimento() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_alimento)
        alerta.setMessage(getString(R.string.msg_pregunta_delete)+" ${args.alimento.alimento}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            alimentoViewModel.deleteAlimento(args.alimento) //efectivamente borra el lugar
            Toast.makeText(requireContext(),getString(R.string.msg_alimento_deleted),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateAlimentoFragment_to_nav_alimento)
        }
        alerta.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alerta.create().show()
    }

    private fun updateAlimento() {
        val nombre=binding.etAlimento.text.toString() //obtienen el texto de lo que el usuario escribio

        if (nombre.isNotEmpty()){
            val calorias=binding.etCalorias.text.toString() //obtienen el texto de lo que el usuario escribio

            val alimento = Alimento(args.alimento.id,nombre,calorias)

            //se procede a actualizar el lugar
            alimentoViewModel.saveAlimento(alimento)
            Toast.makeText(requireContext(),getString(R.string.msg_alimento_updated),Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateAlimentoFragment_to_nav_alimento)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}