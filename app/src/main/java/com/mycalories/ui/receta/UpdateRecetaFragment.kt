package com.example.recetas.ui.receta

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
import com.mycalories.R
import com.mycalories.databinding.FragmentUpdateRecetaBinding
import com.mycalories.model.Receta
import com.mycalories.viewmodel.RecetaViewModel


class UpdateRecetaFragment : Fragment() {


    //se define un obketo para obtener los argumentos pasados al fragmento
    private val args by navArgs<UpdateRecetaFragmentArgs>()

    //objeto que interactuca con la tabla
    private  lateinit var recetaViewModel: RecetaViewModel

    private var _binding: FragmentUpdateRecetaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         recetaViewModel = ViewModelProvider(this).get(RecetaViewModel::class.java)

        _binding = FragmentUpdateRecetaBinding.inflate(inflater, container, false)

        binding.etNombrereceta.setText(args.receta.nombrereceta)
        binding.etReceta.setText(args.receta.contenidoreceta)


        binding.btUpdate.setOnClickListener{ updateReceta() }
        binding.btDelete.setOnClickListener{ deleteReceta() }


        return binding.root
    }


    private fun deleteReceta() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_receta)
        alerta.setMessage(getString(R.string.msg_preguntaRe_delete)+" ${args.receta.nombrereceta}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            recetaViewModel.deleteReceta(args.receta) //efectivamente borra la receta
            Toast.makeText(requireContext(),getString(R.string.msg_receta_deleted),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRecetaFragment_to_nav_receta)
        }
        alerta.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alerta.create().show()
    }

    private fun updateReceta() {
        val nombre=binding.etNombrereceta.text.toString() //obtienen el texto de lo que el usuario escribio

        if (nombre.isNotEmpty()){
            val contenido=binding.etReceta.text.toString() //obtienen el texto de lo que el usuario escribio

            val receta = Receta(args.receta.id,nombre,contenido)

            //se procede a actualizar el lugar
            recetaViewModel.saveReceta(receta)
            Toast.makeText(requireContext(),getString(R.string.msg_receta_updated),Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateRecetaFragment_to_nav_receta)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}