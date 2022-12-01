package com.example.recetas.ui.receta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recetas.R
import com.example.recetas.databinding.FragmentAddRecetaBinding
import com.example.recetas.model.Receta
import com.example.recetas.viewmodel.RecetaViewModel


class AddRecetaFragment : Fragment(){

    //objeto que interactua con la tabla
    private  lateinit var recetaViewModel: RecetaViewModel

    private var _binding: FragmentAddRecetaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recetaViewModel = ViewModelProvider(this).get(RecetaViewModel::class.java)

        _binding = FragmentAddRecetaBinding.inflate(inflater, container, false)

        binding.btAdd.setOnClickListener{ addReceta() }

        return binding.root
    }

    private fun addReceta() {
        val nombre=binding.etNombrereceta.text.toString() //obtienen el texto de lo que el usuario escribio

        if (nombre.isNotEmpty()){
            val contenidoreceta=binding.etReceta.text.toString() //obtienen el texto de lo que el usuario escribio

            val receta = Receta(0,nombre,contenidoreceta)

            //se procede a registrar el nuevo lugar
            recetaViewModel.saveReceta(receta)
            Toast.makeText(requireContext(),getString(R.string.msg_receta_added), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_nav_receta_to_addRecetaFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}