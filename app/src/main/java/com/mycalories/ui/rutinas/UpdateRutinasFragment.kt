package com.mycalories.ui.rutinas

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.AlimentoAdapter
import com.mycalories.databinding.FragmentAddRutinasBinding
import com.mycalories.databinding.FragmentRutinasBinding
import com.mycalories.databinding.FragmentUpdateRutinasBinding
import com.mycalories.model.Rutinas
import com.mycalories.viewmodel.RutinasViewModel


class UpdateRutinasFragment : Fragment() {


    private val args by navArgs<UpdateRutinasFragmentArgs>()

    private lateinit var rutinasViewModel: RutinasViewModel

    private var _binding: FragmentUpdateRutinasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rutinasViewModel =
            ViewModelProvider(this)[RutinasViewModel::class.java]

        _binding = FragmentUpdateRutinasBinding.inflate(inflater, container, false)

        binding.etRutina.setText(args.rutina.nombre)
        binding.etEjeryreps.setText(args.rutina.EjeryReps)
        binding.etReposo.setText(args.rutina.reposo)

        binding.btUpdateR.setOnClickListener{ updateRutina() }
        binding.btDeleteR.setOnClickListener{ deleteRutina() }

        return binding.root
    }

    private fun deleteRutina() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_rutina)
        alerta.setMessage(getString(R.string.msg_preguntaR_deleted)+" ${args.rutina.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            rutinasViewModel.deleteRutinas(args.rutina)
            Toast.makeText(requireContext(),getString(R.string.msg_rutina_deleted),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRutinasFragment_to_rutinasFragment)
        }
        alerta.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alerta.create().show()
    }

    private fun updateRutina(){
        val nombre=binding.etRutina.text.toString()

        if (nombre.isNotEmpty()){
            val ejeryreps= binding.etEjeryreps.text.toString()
            val reposo = binding.etReposo.text.toString()
            val rutina = Rutinas(args.rutina.RId,nombre,ejeryreps,reposo)

            rutinasViewModel.saveRutinas(rutina)
            Toast.makeText(requireContext(),getString(R.string.msg_rutina_updated),Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateRutinasFragment_to_rutinasFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}